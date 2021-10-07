package com.example.FirstLab.managers;

import com.example.FirstLab.models.DTO.PaymentDto;
import com.example.FirstLab.models.Payment;

import java.util.List;
import java.util.stream.Collectors;

public interface IPaymentManager extends IManager{
    public List<PaymentDto> getClientPayments(int clientId);
    public PaymentDto add(int amount, int clientId, String text);
    public boolean delete(int id);
}
