package com.example.demo.utils;

import com.example.demo.models.STS;
import com.example.demo.services.STSsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class STSsValidator implements Validator {
    private final STSsService STSsService;

    @Autowired
    public STSsValidator(STSsService STSsService) {
        this.STSsService = STSsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return STS.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        STS STS = (STS) target;
        if (STSsService.isPresentClientTinToDB(STS.getClientTin()).isPresent())
            errors.rejectValue("clientTin", "", "Такая зарегистрированная точка расчета с такой точкой расчета уже был зарегистрирован в ГНС");
    }
}
