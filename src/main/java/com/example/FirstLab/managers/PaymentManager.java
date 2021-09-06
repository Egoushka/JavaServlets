package com.example.FirstLab.managers;

import com.example.FirstLab.dao.ClientDao;
import com.example.FirstLab.dao.PaymentDao;
import com.example.FirstLab.models.Client;
import com.example.FirstLab.models.Payment;
import com.example.FirstLab.models.dto.ClientDto;
import com.example.FirstLab.models.dto.PaymentDto;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaymentManager {
    PaymentDao dao;
    public PaymentManager() throws SQLException {
        dao = new PaymentDao();
    }
    public List<PaymentDto> getClientPayments(int clientId){
        var payments = dao.getAll();
        var result = payments
                .stream()
                .filter(payment -> payment.getClientId() == clientId);
        return result.map(Payment::fromEntityToDto).collect(Collectors.toList());
    }
    public PaymentDto add(int amount, int clientId, String text){

        var payment = new Payment(amount,clientId,text);

        dao.insert(payment);

        return payment.fromEntityToDto();
    }
    public boolean delete(int id){
        return dao.delete(id);
    }
}
