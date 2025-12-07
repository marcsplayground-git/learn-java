package com.personalplayground.jdbc;

import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class JdbcRowSetDemo {

    public static void main(String[] args) {
        System.out.println("Starting JdbcRowSetDemo...");

        try {
            RowSetFactory factory = RowSetProvider.newFactory();

            // 1. Create JdbcRowSet instance
            try (JdbcRowSet rowSet = factory.createJdbcRowSet()) {

                // 2. Configure connection properties (like a little JavaBean)
                rowSet.setUrl(DatabaseConfig.URL);
                rowSet.setUsername(DatabaseConfig.USER);
                rowSet.setPassword(DatabaseConfig.PASSWORD);

                // 3. Set the SQL command
                String sql = "SELECT productCode, productName, buyPrice " +
                        "FROM products " +
                        "ORDER BY productCode " +
                        "LIMIT 10";
                rowSet.setCommand(sql);

                // 4. Execute – this opens a connection internally and runs the SQL
                rowSet.execute();

                System.out.println("Results from JdbcRowSet (forward):");
                System.out.println("------------------------------------------------------------");

                // 5. Forward iteration (like ResultSet)
                while (rowSet.next()) {
                    String code = rowSet.getString("productCode");
                    String name = rowSet.getString("productName");
                    double price = rowSet.getDouble("buyPrice");

                    System.out.printf("Code: %-10s | Name: %-35s | Price: %.2f%n",
                            code, name, price);
                }

                System.out.println("------------------------------------------------------------");

                // 6. Because JdbcRowSet is scrollable by default, we can go backwards
                System.out.println("Results from JdbcRowSet (backwards):");
                while (rowSet.previous()) {
                    String code = rowSet.getString("productCode");
                    String name = rowSet.getString("productName");
                    double price = rowSet.getDouble("buyPrice");

                    System.out.printf("Code: %-10s | Name: %-35s | Price: %.2f%n",
                            code, name, price);
                }

                System.out.println("------------------------------------------------------------");
                System.out.println("JdbcRowSetDemo finished normally.");
            }

        } catch (SQLException e) {
            System.out.println("Error while using JdbcRowSet!");
            JdbcUtils.printSQLException(e);
        }
    }
}
