package com.example.FirstLab.models;

import com.example.FirstLab.models.dto.ClientDto;
import com.example.FirstLab.models.dto.PaymentDto;

public class Payment {
    int id;
    int amount;
    int clientId;
    String text;

    public Payment(int amount, int clientId, String text) {
        this.amount = amount;
        this.clientId = clientId;
        this.text = text;
    }

    public Payment() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PaymentDto fromEntityToDto(){
        return new PaymentDto(getId(), getAmount() ,getClientId(), getText());
    }
}
