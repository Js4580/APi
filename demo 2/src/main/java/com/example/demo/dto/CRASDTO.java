package com.example.demo.dto;


import com.example.demo.models.STS;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CRASDTO {

    @Min(value = 0, message = "Значение должно быть больше или равно 0")
    private Double sum;
    @NotNull(message = "Наименование поставщика услуг нерезидента КР заполнить")
    private String nonresidentName;
    @NotNull(message = "Регистрационный номер точки расчета не должно оставаться пустым")
    private STS sensor;


    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getNonresidentName() {
        return nonresidentName;
    }

    public void setNonresidentName(String nonresidentName) {
        this.nonresidentName = nonresidentName;
    }

    public STS getPointId() {
        return sensor;
    }

    public void setPointId(STS sensor) {
        this.sensor = sensor;
    }
}
