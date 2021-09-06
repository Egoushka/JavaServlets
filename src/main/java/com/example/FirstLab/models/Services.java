package com.example.FirstLab.models;

import com.example.FirstLab.dao.PaymentDao;

import java.sql.SQLException;

public class Services {
    private PaymentDao paymentDao;
    public Services() throws SQLException {
        paymentDao = new PaymentDao();
    }
    void makeOperation(Payment payment){
        paymentDao.insert(payment);
    }
}
