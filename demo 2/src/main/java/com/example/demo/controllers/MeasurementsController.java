package com.example.demo.controllers;

import com.example.demo.dto.MeasurementDTO;
import com.example.demo.dto.MeasurementResponse;
import com.example.demo.models.Measurement;
import com.example.demo.services.MeasurementsService;
import com.example.demo.utils.ErrorField;
import com.example.demo.utils.MeasurementException;
import com.example.demo.utils.MeasurementExceptionResponse;
import com.example.demo.utils.MeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    public MeasurementsController(MeasurementsService measurementsService,
                                  MeasurementValidator measurementValidator, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public MeasurementResponse allMeasurements(){
        return new MeasurementResponse(measurementsService.allMeasurements().stream()
                .map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public long getRainyDaysCount(){
        return measurementsService.allMeasurements().stream().filter(Measurement::getRaining).count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult){
        Measurement measurementFromMeasurementDTO = convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurementFromMeasurementDTO, bindingResult);
        if (bindingResult.hasErrors())
            ErrorField.getErrorField(bindingResult);
        measurementsService.addMeasurement(measurementFromMeasurementDTO);
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

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
