package com.personalplayground.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateNoteDemo {

    public static void main(String[] args) {
        System.out.println("Starting UpdateNoteDemo...");

        String url = DatabaseConfig.URL;
        String user = DatabaseConfig.USER;
        String password = DatabaseConfig.PASSWORD;

        // Example: update note with id = 1
        updateNote(url, user, password,
                1L,
                "Updated title from Java",
                "This note was updated via JDBC using a PreparedStatement.");

        // Example: update note with id = 9999 (likely does not exist) to see the effect
        updateNote(url, user, password,
                9999L,
                "Nonexistent note",
                "This update should affect 0 rows, because id 9999 probably does not exist.");

        System.out.println("UpdateNoteDemo finished.");
    }

    private static void updateNote(String url, String user, String password,
                                   long id, String newTitle, String newBody) {

        String sql = "UPDATE learning_notes " +
                "SET title = ?, body = ? " +
                "WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("Attempting to update note with id = " + id);
            System.out.println("New title: " + newTitle);
            System.out.println("New body: " + newBody);

            // 1. Bind parameters
            ps.setString(1, newTitle);
            ps.setString(2, newBody);
            ps.setLong(3, id);

            // 2. Execute update
            int affectedRows = ps.executeUpdate();
            System.out.println("Rows affected by UPDATE: " + affectedRows);

            if (affectedRows == 0) {
                System.out.println("No note found with id = " + id + ". Nothing was updated.");
            } else if (affectedRows == 1) {
                System.out.println("Successfully updated note with id = " + id);
            } else {
                System.out.println("Warning: more than one row was updated (" + affectedRows + "). Check your WHERE clause!");
            }

        } catch (SQLException e) {
            System.out.println("Error while updating note!");
            System.out.println("Message: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
        }

        System.out.println("Update operation finished for id = " + id);
        System.out.println("--------------------------------------------------");
    }
}
