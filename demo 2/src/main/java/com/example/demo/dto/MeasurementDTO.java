package com.example.demo.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {
    @Min(value = -100, message = "Значение должно быть больше или равно -100")
    @Max(value = 100, message = "Значение должно быть меньше или равно 100")
    @NotNull(message = "Это поле не должно оставаться пустым value")
    private Double value;
    @NotNull(message = "Это поле не должно оставаться пустым: true или false должно присутсвовать")
    private Boolean raining;
    @NotNull(message = "Это поле не должно оставаться пустым sensor")
    private SensorDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
