package com.personalplayground.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    public static void main(String[] args) {
        System.out.println("Starting JDBC connection test...");

        // 1. Read config
        String url = DatabaseConfig.URL;
        String user = DatabaseConfig.USER;
        String password = DatabaseConfig.PASSWORD;

        // 2. Try-with-resources: Connection will be closed automatically
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection object created: " + conn);

            // 3. Check if the connection is actually valid (ping-like check)
            boolean valid = conn.isValid(2); // timeout in seconds
            System.out.println("Is connection valid? " + valid);

        } catch (SQLException e) {
            System.out.println("Error while connecting to database!");
            System.out.println("Message: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace(); // For learning: see the full stack trace
        }

        System.out.println("JDBC connection test finished.");
    }
}
