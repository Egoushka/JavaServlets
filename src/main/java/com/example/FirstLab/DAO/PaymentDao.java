package com.example.FirstLab.DAO;

import com.example.FirstLab.DBUtils.ConnectionFactory;
import com.example.FirstLab.DBUtils.DBUtils;
import com.example.FirstLab.DBUtils.PaymentDBUtils;
import com.example.FirstLab.models.Payment;
import com.example.FirstLab.qualifiers.PaymentDaoQualifier;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDao implements IPaymentDao{
    Connection connection;
    Statement statement;

    public PaymentDao() throws SQLException {
        connection = ConnectionFactory.getConnection();
        statement = connection.createStatement();
    }

    @Override
    public Optional<Payment> get(int id) {
        Optional<Payment> result = Optional.empty();
        try {
            String query = "SELECT * FROM [Payment] WHERE id=?";

            PreparedStatement statement  = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

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

    @Override
    public List<Payment> getAll() {
        List<Payment> payments = new ArrayList<>();
        try {
            String query = "SELECT * FROM [Payment]";

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

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

    @Override
    public boolean insert(Payment payment) {
        try
        {
            String query = "INSERT INTO Payment(amount, text, clientId) VALUES(?,?,?)";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1,payment.getAmount());
            statement.setString(2,payment.getText());
            statement.setInt(3, payment.getClientId());

            statement.execute();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        System.out.println("Payment inserted");
        return true;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try
        {
            String query = "DELETE FROM Payment WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);
            result = statement.execute();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Payment deleted");
        return result;
    }

    @Override
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
