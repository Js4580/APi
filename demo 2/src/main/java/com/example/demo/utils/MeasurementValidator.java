package com.example.demo.utils;

import com.example.demo.models.Measurement;
import com.example.demo.services.STSsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final STSsService STSsService;

    @Autowired
    public MeasurementValidator(STSsService STSsService) {
        this.STSsService = STSsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (measurement.getPointId() == null) return;
        if (STSsService.isPresentClientTinToDB(measurement.getPointId().getClientTin()).isEmpty())
            errors.rejectValue("pointId","","Такой регистрационный номер точки расчета не зарегистрирован в нашем ГНС");
    }
}
