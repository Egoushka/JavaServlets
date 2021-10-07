package com.example.FirstLab.models;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Payment {
    @NotNull
    int id;
    @NotNull
    int amount;
    @NotNull
    int clientId;
    @NotNull
    String text;

    public Payment(int amount, int clientId, String text) {
        this.amount = amount;
        this.clientId = clientId;
        this.text = text;
    }
    public Payment(){}
}
