package com.example.demo.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "sts")
public class STS implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "clienttin")
    @NotEmpty(message = "ИНН клиента, обязательно к заполнению")
    @Size(max = 14, message = "Банки указывают получателя безналичного платежа и длина не больше 14")
    private String clientTin;

    @Column(name = "clientname")
    @NotEmpty(message = "Наименование клиента, обязательно к заполнению.")
    private String clientName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientTin() {
        return clientTin;
    }

    public void setClientTin(String clientTin) {
        this.clientTin = clientTin;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
