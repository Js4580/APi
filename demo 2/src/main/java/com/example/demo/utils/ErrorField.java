package com.example.demo.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorField {
    public static void getErrorField(BindingResult bindingResult){
        StringBuilder stringErrors = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error :
                errors) {
            stringErrors.append("поле: ").append(error.getField()).append(" - ")
                    .append(error.getDefaultMessage()).append(";");
        }
        throw new MeasurementException(stringErrors.toString());
    }
}
