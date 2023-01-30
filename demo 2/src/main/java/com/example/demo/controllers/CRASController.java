package com.example.demo.controllers;

import com.example.demo.dto.CRASDTO;
import com.example.demo.models.CRAS;
import com.example.demo.services.CRASService;
import com.example.demo.utils.ErrorField;
import com.example.demo.utils.CRASException;
import com.example.demo.utils.CRASExceptionResponse;
import com.example.demo.utils.CRASValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class CRASController {
    private final CRASService CRASService;
    private final CRASValidator CRASValidator;
    private final ModelMapper modelMapper;

    public CRASController(CRASService CRASService,
                          CRASValidator CRASValidator, ModelMapper modelMapper) {
        this.CRASService = CRASService;
        this.CRASValidator = CRASValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/operations")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid CRASDTO CRASDTO,
                                                     BindingResult bindingResult){
        CRAS measurementFromCRASDTO = convertToMeasurement(CRASDTO);
        CRASValidator.validate(measurementFromCRASDTO, bindingResult);
        if (bindingResult.hasErrors())
            ErrorField.getErrorField(bindingResult);
        CRASService.addMeasurement(measurementFromCRASDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<CRASExceptionResponse> handleException(CRASException e){
        CRASExceptionResponse CRASExceptionResponse = new CRASExceptionResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(CRASExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private CRAS convertToMeasurement(CRASDTO CRASDTO) {
        return modelMapper.map(CRASDTO, CRAS.class);
    }

    private CRASDTO convertToMeasurementDTO(CRAS CRAS) {
        return modelMapper.map(CRAS, CRASDTO.class);
    }
}
