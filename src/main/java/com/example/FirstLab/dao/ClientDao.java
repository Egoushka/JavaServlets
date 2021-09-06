package com.example.FirstLab.dao;

import com.example.FirstLab.DBUtils.ClientDBUtils;
import com.example.FirstLab.DBUtils.ConnectionFactory;
import com.example.FirstLab.DBUtils.DBUtils;
import com.example.FirstLab.models.Client;
import com.example.FirstLab.models.Payment;
import com.example.FirstLab.models.dto.ClientDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM [Client] WHERE id=" + id);

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
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM [Client]");

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

    public Client insert(Client client) {
        boolean result = false;
        try {
            Statement statement = connection.createStatement();
            result = statement.execute(
                    "INSERT INTO Client(name, email, password) " +
                            "VALUES('" + client.getName() + "'," +
                            "'" + client.getEmail() + "'," +
                            "'" + client.getPassword() + "'"+
                            ")");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Client inserted");
        return client;
    }

    public Client update(Client client) {
        boolean result = false;
        try {
            Statement statement = connection.createStatement();
            result = statement.execute(
                    "UPDATE Client" +
                            "SET Name = '" + client.getName() + "'" +
                            "email = '" + client.getEmail() + "'"+
                            "password = '" + client.getPassword() + "'"+
                            "WHERE id = " + client.getId() + ";");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return client;
    }

    public boolean delete(int id) {
        boolean result = false;
        try {
            result = statement.execute(
                    "DELETE FROM Client WHERE id=" + id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Client deleted");
        return result;
    }

    public boolean clearData() {
        boolean result = false;
        try {
            result = statement.execute(
                    "DELETE FROM Client");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
