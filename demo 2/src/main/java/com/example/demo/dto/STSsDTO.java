package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class STSsDTO {

    @NotEmpty(message = "ИНН клиента, обязательно к заполнению")
    @Size(max = 14, message = "Банки указывают получателя безналичного платежа и длина не больше 14")
    private String clientTin;

    @NotEmpty(message = "Наименование клиента, обязательно к заполнению.")
    private String clientName;

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
