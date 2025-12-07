package com.personalplayground.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedSelectDemo {

    public static void main(String[] args) {
        System.out.println("Starting PreparedSelectDemo (with connection pool)...");

        double minPrice = 50.0;
        double maxPrice = 100.0;

        String sql = "SELECT productCode, productName, buyPrice " +
                "FROM products " +
                "WHERE buyPrice BETWEEN ? AND ? " +
                "ORDER BY buyPrice";

        // 1. Get DataSource from our ConnectionPool utility
        DataSource ds = ConnectionPool.getDataSource();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("Got connection from pool: " + conn);
            System.out.println("Using SQL:");
            System.out.println(sql);
            System.out.println("With parameters: minPrice=" + minPrice + ", maxPrice=" + maxPrice);

            // 2. Bind parameters
            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);

            // 3. Execute query
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Results:");
                System.out.println("------------------------------------------------------------");

                while (rs.next()) {
                    String code = rs.getString("productCode");
                    String name = rs.getString("productName");
                    double price = rs.getDouble("buyPrice");

                    System.out.printf("Code: %-10s | Name: %-35s | Price: %.2f%n",
                            code, name, price);
                }

                System.out.println("------------------------------------------------------------");
                System.out.println("Query finished.");
            }

        } catch (SQLException e) {
            System.out.println("Error while running prepared SELECT query with pool!");
            JdbcUtils.printSQLException(e);
        }

        System.out.println("PreparedSelectDemo finished.");
    }
}
