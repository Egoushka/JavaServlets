package com.example.FirstLab.dao;

import com.example.FirstLab.DBUtils.ClientDBUtils;
import com.example.FirstLab.DBUtils.ConnectionFactory;
import com.example.FirstLab.DBUtils.DBUtils;
import com.example.FirstLab.models.Client;
import com.example.FirstLab.models.Payment;
import com.example.FirstLab.models.dto.ClientDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientDao {
    Connection connection;
    Statement statement;

    public ClientDao() throws SQLException {
        connection = ConnectionFactory.getConnection();
        statement = connection.createStatement();
    }
    public Optional<Client> get(int id) {
        Optional<Client> result = Optional.empty();
        try {
            String query = "SELECT * FROM [Client] WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                result = ClientDBUtils.getClient(rs);
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

    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try {
            String query = "SELECT * FROM [Client]";

            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                var client = ClientDBUtils.getClient(rs);
                client.ifPresent(clients::add);
            }

            DBUtils.close(rs);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clients;
    }

    public boolean insert(Client client) {
        try {
            String query = "INSERT INTO Client(name, email, password) VALUES(?, ?,?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPassword());

            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Client inserted");
        return true;
    }
    public boolean delete(int id) {
        boolean result = false;
        try {
            String query = "DELETE FROM Client WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);

            result = statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Client deleted");
        return result;
    }

    public boolean clearData() {
        boolean result = false;
        try {
            String query = "DELETE FROM Client";

            PreparedStatement statement = connection.prepareStatement(query);

            result = statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
