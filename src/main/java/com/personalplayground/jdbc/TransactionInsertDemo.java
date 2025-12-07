package com.personalplayground.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionInsertDemo {

    public static void main(String[] args) {
        System.out.println("Starting TransactionInsertDemo...");

        String url = DatabaseConfig.URL;
        String user = DatabaseConfig.USER;
        String password = DatabaseConfig.PASSWORD;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            System.out.println("Connected to database.");
            System.out.println("Default autoCommit = " + conn.getAutoCommit());

            // 1. Turn off auto-commit to start a manual transaction
            conn.setAutoCommit(false);
            System.out.println("autoCommit turned OFF.");

            try {
                // 2. Perform multiple operations in the same transaction
                insertNote(conn, "Tx note A", "First note in a transaction.");
                insertNote(conn, "Tx note B", "Second note in the same transaction.");

                // 3. If everything went well, commit the transaction
                conn.commit();
                System.out.println("Transaction committed successfully.");

            } catch (SQLException e) {
                // 4. If something fails, rollback everything
                System.out.println("Error occurred during transaction. Rolling back...");
                try {
                    conn.rollback();
                    System.out.println("Rollback completed.");
                } catch (SQLException rollbackEx) {
                    System.out.println("Error during rollback: " + rollbackEx.getMessage());
                    rollbackEx.printStackTrace();
                }

                // Re-throw or log original error for debugging
                System.out.println("Original error message: " + e.getMessage());
                e.printStackTrace();
            } finally {
                // 5. Important: turn autoCommit back ON before returning connection to a pool (in big apps)
                conn.setAutoCommit(true);
                System.out.println("autoCommit turned back ON.");
            }

        } catch (SQLException e) {
            System.out.println("Error while setting up connection or transaction!");
            System.out.println("Message: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
        }

        System.out.println("TransactionInsertDemo finished.");
    }

    private static void insertNote(Connection conn, String title, String body) throws SQLException {
        String sql = "INSERT INTO learning_notes (title, body) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            System.out.println("Inserting note inside transaction...");
            System.out.println("    Title: " + title);

            ps.setString(1, title);
            ps.setString(2, body);

            int affectedRows = ps.executeUpdate();
            System.out.println("    Rows affected by INSERT: " + affectedRows);

            if (affectedRows != 1) {
                throw new SQLException("Unexpected affected rows count: " + affectedRows);
            }

            // Force an error when inserting a specific title
            if (title.contains("B")) {
                throw new SQLException("Simulated error after inserting note B");
            }
        }
    }

}
