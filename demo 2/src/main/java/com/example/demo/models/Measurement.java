package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurementsts")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sum")
    @NotNull(message = "Сумма операции не должно быть пустым")
    @Min(value = 0, message = "Значение должно быть больше или равно 0")
    private Double sum;
    @Column(name = "nonresidentName")
    @NotNull(message = "Наименование поставщика услуг нерезидента КР заполнить")
    private String nonresidentName;
    @Column(name = "measurement_date_time")
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "pointId", referencedColumnName = "clientTin")
    @NotNull(message = "Регистрационный номер точки расчета не должно оставаться пустым")
    private STS pointId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public STS getPointId() {
        return pointId;
    }

    public void setPointId(STS pointId) {
        this.pointId = pointId;
    }
}
