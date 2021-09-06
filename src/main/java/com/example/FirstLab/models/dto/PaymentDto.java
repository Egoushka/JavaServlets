package com.example.FirstLab.models.dto;

import com.example.FirstLab.models.Payment;
import lombok.Data;

@Data
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
    public static PaymentDto fromEntityToDto(Payment payment){
        return new PaymentDto(payment.getId(), payment.getAmount() ,payment.getClientId(), payment.getText());
    }

}
