package com.example.FirstLab.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    public static void close(Connection connection){
        if(connection == null) return;
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void close(ResultSet resultSet){
        if(resultSet == null) return;
        try {
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void initBD(Connection connection) throws SQLException {
        dropDBTable(connection);
        Statement stmt = connection.createStatement();
        executeQuery(stmt,
                "CREATE TABLE IF NOT EXISTS Client (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT NOT NULL)");
        stmt.close();
    }
    public static void dropDBTable(Connection connection) throws SQLException {
        Statement  stmt = connection.createStatement();
        executeQuery(stmt,
                "drop table if exists Animal;drop table if exists AnimalBehavior; drop table if exists CageBehavior;");
    }
    public static void executeQuery(Statement stmt, String query) throws SQLException {
        stmt.executeUpdate(query);
    }
}
