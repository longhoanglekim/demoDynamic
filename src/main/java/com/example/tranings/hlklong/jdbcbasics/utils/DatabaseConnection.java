package com.example.tranings.hlklong.jdbcbasics.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb",
                "root", "Long24062004");
    }
}
