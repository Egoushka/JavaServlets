package com.example.FirstLab.DBUtils;

import com.example.FirstLab.models.Client;
import com.example.FirstLab.models.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentDBUtils {
    public static Optional<Payment> getPayment(ResultSet paymentResultSet) throws SQLException {
        Payment payment = new Payment();

        payment.setId(paymentResultSet.getInt("id"));
        payment.setAmount(paymentResultSet.getInt("amount"));
        payment.setText(paymentResultSet.getString("text"));
        payment.setClientId(paymentResultSet.getInt("clientId"));

        return Optional.of(payment);
    }
}
