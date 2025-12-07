package com.personalplayground.jdbc;

import javax.sql.DataSource;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CachedRowSetDemo {

    public static void main(String[] args) {
        System.out.println("Starting CachedRowSetDemo...");

        DataSource ds = ConnectionPool.getDataSource();

        String sql = "SELECT productCode, productName, buyPrice " +
                "FROM products " +
                "WHERE buyPrice BETWEEN ? AND ? " +
                "ORDER BY buyPrice " +
                "LIMIT 20";

        double minPrice = 20.0;
        double maxPrice = 80.0;


        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("Connected and executing initial query for CachedRowSet...");

            ps.setDouble(1, minPrice);
            ps.setDouble(2, maxPrice);

            try (ResultSet rs = ps.executeQuery()) {

                // 1. Create CachedRowSet
                RowSetFactory factory = RowSetProvider.newFactory();
                CachedRowSet cached = factory.createCachedRowSet();

                // 2. Populate from the live ResultSet
                cached.populate(rs);

                System.out.println("Data copied into CachedRowSet. Closing connection now...");
                // After this try-with-resources ends, conn and rs will be closed.

                // 3. Use the CachedRowSet after connection is closed
                System.out.println("Iterating CachedRowSet (disconnected):");
                System.out.println("------------------------------------------------------------");

                while (cached.next()) {
                    String code = cached.getString("productCode");
                    String name = cached.getString("productName");
                    double price = cached.getDouble("buyPrice");

                    System.out.printf("Code: %-10s | Name: %-35s | Price: %.2f%n",
                            code, name, price);
                }

                System.out.println("------------------------------------------------------------");
                System.out.println("CachedRowSetDemo finished.");
            }

        } catch (SQLException e) {
            System.out.println("Error while using CachedRowSet!");
            JdbcUtils.printSQLException(e);
        }
    }
}
