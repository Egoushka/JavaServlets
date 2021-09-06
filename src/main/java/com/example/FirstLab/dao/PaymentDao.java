package com.example.FirstLab.dao;

import com.example.FirstLab.DBUtils.ConnectionFactory;
import com.example.FirstLab.DBUtils.DBUtils;
import com.example.FirstLab.DBUtils.PaymentDBUtils;
import com.example.FirstLab.models.Payment;
import com.example.FirstLab.models.dto.PaymentDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentDao{
    Connection connection;
    Statement statement;

    public PaymentDao() throws SQLException {
        connection = ConnectionFactory.getConnection();
        statement = connection.createStatement();
    }

    public Optional<Payment> get(int id) {
        Optional<Payment> result = Optional.empty();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM [Payment] WHERE id=" + id);

            if (rs.next()) {
                result = PaymentDBUtils.getPayment(rs);
                if(result.isEmpty()){
                    return Optional.empty();
                }
            }
            DBUtils.close(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public List<Payment> getAll() {
        List<Payment> payments = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM [Payment]");

            while (rs.next()) {
                var payment = PaymentDBUtils.getPayment(rs);
                payment.ifPresent(payments::add);
            }

            DBUtils.close(rs);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return payments;
    }

    public boolean insert(Payment payment) {
        try
        {
            Statement statement = connection.createStatement();
             statement.execute(
                    "INSERT INTO Payment(amount, text, clientId) " +
                            "VALUES(" + payment.getAmount() + ",'" + payment.getText() + "'," + payment.getClientId() + ");");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        System.out.println("Payment inserted");
        return true;
    }

    public boolean update(Payment payment) {
        boolean result = false;
        try
        {
            Statement statement = connection.createStatement();
            result = statement.execute(
                    "UPDATE Payment" +
                            "SET Name = '" + payment.getAmount() + "'" +
                            "WHERE id = " + payment.getId() + ";");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try
        {
            result = statement.execute(
                    "DELETE FROM Payment WHERE id=" + id);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Payment deleted");
        return result;
    }

    public boolean clearData() {
        boolean result = false;
        try
        {
            result = statement.execute(
                    "DELETE FROM Payment");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
