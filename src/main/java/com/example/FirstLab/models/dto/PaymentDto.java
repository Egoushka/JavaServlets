package com.example.FirstLab.models.dto;

public class PaymentDto {
    int id;
    int amount;
    int clientId;
    String text;

    public PaymentDto(int id, int amount, int clientId, String text) {
        this.id = id;
        this.amount = amount;
        this.clientId = clientId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
