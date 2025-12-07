package com.personalplayground.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertNoteDemo {

    public static void main(String[] args) {
        System.out.println("Starting InsertNoteDemo...");

        String url = DatabaseConfig.URL;
        String user = DatabaseConfig.USER;
        String password = DatabaseConfig.PASSWORD;

        // Example notes to insert
        insertNote(url, user, password,
                "First JDBC note",
                "This note was inserted using a PreparedStatement with generated keys.");

        insertNote(url, user, password,
                "Another JDBC note",
                "This is the second note inserted from Java.");

        System.out.println("InsertNoteDemo finished.");
    }

    private static void insertNote(String url, String user, String password,
                                   String title, String body) {

        String sql = "INSERT INTO learning_notes (title, body) VALUES (?, ?)";

        // Try-with-resources for Connection and PreparedStatement
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            System.out.println("Connected. Inserting a new note...");
            System.out.println("Title: " + title);
            System.out.println("Body: " + body);

            // 1. Bind parameters
            ps.setString(1, title);
            ps.setString(2, body);

            // 2. Execute and get update count
            int affectedRows = ps.executeUpdate();
            System.out.println("Rows affected by INSERT: " + affectedRows);

            if (affectedRows == 0) {
                System.out.println("Warning: no rows were inserted!");
            }

            // 3. Retrieve generated keys (auto-increment ID)
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long newId = keys.getLong(1); // first column in generated keys
                    System.out.println("New note ID (generated key): " + newId);
                } else {
                    System.out.println("No generated key returned.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while inserting note!");
            System.out.println("Message: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
        }

        System.out.println("Insert method finished for title: " + title);
        System.out.println("--------------------------------------------------");
    }
}
