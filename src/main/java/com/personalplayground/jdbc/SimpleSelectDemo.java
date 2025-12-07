package com.personalplayground.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleSelectDemo {

    public static void main(String[] args) {
        System.out.println("Starting SimpleSelectDemo...");

        // 1. Read database config
        String url = DatabaseConfig.URL;
        String user = DatabaseConfig.USER;
        String password = DatabaseConfig.PASSWORD;

        // 2. Define our SQL query (adjust table/column names if needed)
        String sql = "SELECT productCode, productName, buyPrice " +
                "FROM products " +
                "ORDER BY productCode " +
                "LIMIT 10";

        // 3. Try-with-resources for Connection and Statement
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            System.out.println("Connected to database. Executing query:");
            System.out.println(sql);

            // 4. Execute the query and get a ResultSet
            try (ResultSet rs = stmt.executeQuery(sql)) {

                System.out.println("Results:");
                System.out.println("---------------------------------------------");

                // 5. Iterate over rows
                while (rs.next()) {
                    // Read columns by name
                    String code = rs.getString("productCode");
                    String name = rs.getString("productName");
                    double price = rs.getDouble("buyPrice");

                    // 6. Print a formatted line
                    System.out.printf("Code: %-10s | Name: %-40s | Price: %.2f%n",
                            code, name, price);
                }

                System.out.println("---------------------------------------------");
                System.out.println("Query finished.");
            }

        } catch (SQLException e) {
            System.out.println("Error while running SELECT query!");
            System.out.println("Message: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
        }

        System.out.println("SimpleSelectDemo finished.");
    }
}
