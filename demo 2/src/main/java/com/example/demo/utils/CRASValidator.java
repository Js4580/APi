package com.example.demo.utils;

import com.example.demo.models.CRAS;
import com.example.demo.services.STSsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CRASValidator implements Validator {
    private final STSsService STSsService;

    @Autowired
    public CRASValidator(STSsService STSsService) {
        this.STSsService = STSsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CRAS.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CRAS CRAS = (CRAS) target;
        if (CRAS.getPointId() == null) return;
        if (STSsService.isPresentClientTinToDB(CRAS.getPointId().getClientTin()).isEmpty())
            errors.rejectValue("pointId","","Такой регистрационный номер точки расчета не зарегистрирован в нашем ГНС");
    }
}
