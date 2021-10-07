package com.example.FirstLab.DAO;

import com.example.FirstLab.DBUtils.ClientDBUtils;
import com.example.FirstLab.DBUtils.ConnectionFactory;
import com.example.FirstLab.DBUtils.DBUtils;
import com.example.FirstLab.models.Client;
import com.example.FirstLab.qualifiers.ClientDaoQualifier;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Default
public class ClientDao implements IClientDao{
    Connection connection;
    Statement statement;

    @Inject
    public ClientDao() throws SQLException {
        setConnection(ConnectionFactory.getConnection());
        setStatement(getConnection().createStatement());
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    @Override
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

    @Override
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

    @Override
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
    @Override
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

    @Override
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
