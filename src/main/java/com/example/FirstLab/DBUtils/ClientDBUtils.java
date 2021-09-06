package com.example.FirstLab.DBUtils;

import com.example.FirstLab.models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ClientDBUtils {
    public static Optional<Client> getClient(ResultSet clientResultSet) throws SQLException {
        Client client = new Client(
                clientResultSet.getInt("id"),
                clientResultSet.getString("name"),
                clientResultSet.getString("email"),
                clientResultSet.getString("password")
        );

        return Optional.of(client);
    }
}
