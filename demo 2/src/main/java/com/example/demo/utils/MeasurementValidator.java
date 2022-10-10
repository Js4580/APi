package com.example.demo.utils;

import com.example.demo.models.Measurement;
import com.example.demo.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (measurement.getSensor() == null) return;
        if (sensorsService.isPresentSensorToDB(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor","","Такой сенсор не зарегистрирован в нашем API");
    }
}
