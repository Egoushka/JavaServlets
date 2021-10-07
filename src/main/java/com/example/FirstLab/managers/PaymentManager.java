package com.example.FirstLab.managers;

import com.example.FirstLab.DAO.IDao;
import com.example.FirstLab.models.Payment;
import com.example.FirstLab.models.DTO.PaymentDto;
import com.example.FirstLab.qualifiers.PaymentDaoQualifier;
import com.example.FirstLab.qualifiers.PaymentManagerQualifier;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class PaymentManager implements IPaymentManager{
    IDao<Payment> dao;
    public List<PaymentDto> getClientPayments(int clientId){
        var payments = dao.getAll();
        var result = payments
                .stream()
                .filter(payment -> payment.getClientId() == clientId);
        return result.map(PaymentDto::fromEntityToDto).collect(Collectors.toList());
    }
    public PaymentDto add(int amount, int clientId, String text){

        var payment = new Payment(amount,clientId,text);

        dao.insert(payment);

        return PaymentDto.fromEntityToDto(payment);
    }
    public boolean delete(int id){
        return dao.delete(id);
    }
}
