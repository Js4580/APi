package com.example.demo.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MeasurementDTO {

    @NotNull(message = "Сумма операции не должно быть пустым")
    @Min(value = 0, message = "Значение должно быть больше или равно 0")
    private Double sum;

    @NotNull(message = "Наименование поставщика услуг нерезидента КР заполнить")
    private String nonresidentName;

    @NotNull(message = "Регистрационный номер точки расчета не должно оставаться пустым")
    private STSsDTO pointId;

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

    public STSsDTO getPointId() {
        return pointId;
    }

    public void setPointId(STSsDTO pointId) {
        this.pointId = pointId;
    }
}
