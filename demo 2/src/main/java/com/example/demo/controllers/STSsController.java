package com.example.demo.controllers;

import com.example.demo.dto.STSDTO;
import com.example.demo.dto.STSResponse;
import com.example.demo.models.STS;
import com.example.demo.services.STSsService;
import com.example.demo.utils.ErrorField;
import com.example.demo.utils.CRASException;
import com.example.demo.utils.CRASExceptionResponse;
import com.example.demo.utils.STSsValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<String,String> registration(@RequestBody @Valid STSDTO STSDTO, BindingResult bindingResult){
        STS sensorFromSTSDTO = convertToSensor(STSDTO);
        STSsValidator.validate(sensorFromSTSDTO, bindingResult);
        if (bindingResult.hasErrors())
            ErrorField.getErrorField(bindingResult);
        STSsService.registration(sensorFromSTSDTO);
        return Map.of("pointId", STSDTO.getClientTin());
    }

    @GetMapping("/list")
    public STSResponse getAll(){
        return new STSResponse(STSsService.getAll().stream()
                .map(this::covertToSTSDTO).collect(Collectors.toList()));
    }
    @ExceptionHandler
    private ResponseEntity<CRASExceptionResponse> handleException(CRASException e){
        CRASExceptionResponse CRASExceptionResponse = new CRASExceptionResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(CRASExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private STS convertToSensor(STSDTO STSDTO) {
        return modelMapper.map(STSDTO, STS.class);
    }

    private STSDTO covertToSTSDTO(STS sts){
        return modelMapper.map(sts, STSDTO.class);
    }
}
