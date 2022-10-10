package com.example.demo.controllers;

import com.example.demo.dto.SensorDTO;
import com.example.demo.models.Measurement;
import com.example.demo.models.Sensor;
import com.example.demo.services.SensorsService;
import com.example.demo.utils.ErrorField;
import com.example.demo.utils.MeasurementException;
import com.example.demo.utils.MeasurementExceptionResponse;
import com.example.demo.utils.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorsController(SensorsService sensorsService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        Sensor sensorFromSensorDTO = convertToSensor(sensorDTO);
        sensorValidator.validate(sensorFromSensorDTO, bindingResult);
        if (bindingResult.hasErrors())
            ErrorField.getErrorField(bindingResult);
        sensorsService.registration(sensorFromSensorDTO);
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

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
