package com.example.FirstLab.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();
    public static String URL = "jdbc:sqlite:identifier.sqlite";
    public static String DRIVER_CLASS = "org.sqlite.JDBC";

    private ConnectionFactory() {

    }

    public Connection createConnection() {
        Connection connection;
        try {
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(URL);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }
}
