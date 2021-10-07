package com.example.FirstLab.DBUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton
public class ConnectionFactory {

    private static ConnectionFactory instance;
    public static String URL = "jdbc:sqlite:identifier.sqlite";
    public static String DRIVER_CLASS = "org.sqlite.JDBC";
    private Connection connection;

    static {
        instance = new ConnectionFactory();
        instance.connection = instance.createConnection();
    }
    @Inject
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
        return instance.connection;
    }
}
