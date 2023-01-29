package com.example.demo.controllers;

import com.example.demo.dto.STSsDTO;
import com.example.demo.models.STS;
import com.example.demo.services.STSsService;
import com.example.demo.utils.ErrorField;
import com.example.demo.utils.MeasurementException;
import com.example.demo.utils.MeasurementExceptionResponse;
import com.example.demo.utils.STSsValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/point")
public class STSsController {
    private final STSsService STSsService;
    private final STSsValidator STSsValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public STSsController(STSsService STSsService, STSsValidator STSsValidator, ModelMapper modelMapper) {
        this.STSsService = STSsService;
        this.STSsValidator = STSsValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid STSsDTO STSsDTO, BindingResult bindingResult){
        STS sensorFromSTSDTO = convertToSensor(STSsDTO);
        STSsValidator.validate(sensorFromSTSDTO, bindingResult);
        if (bindingResult.hasErrors())
            ErrorField.getErrorField(bindingResult);
        STSsService.registration(sensorFromSTSDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementExceptionResponse> handleException(MeasurementException e){
        MeasurementExceptionResponse measurementExceptionResponse = new MeasurementExceptionResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(measurementExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private STS convertToSensor(STSsDTO STSsDTO) {
        return modelMapper.map(STSsDTO, STS.class);
    }
}
