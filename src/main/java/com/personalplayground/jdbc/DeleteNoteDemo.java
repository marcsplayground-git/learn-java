package com.personalplayground.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteNoteDemo {

    public static void main(String[] args) {
        System.out.println("Starting DeleteNoteDemo...");

        String url = DatabaseConfig.URL;
        String user = DatabaseConfig.USER;
        String password = DatabaseConfig.PASSWORD;

        // 1. Try deleting an existing note (change the ID to one that exists)
        deleteNoteById(url, user, password, 1L);

        // 2. Try deleting a non-existing note (to see what happens)
        deleteNoteById(url, user, password, 9999L);

        System.out.println("DeleteNoteDemo finished.");
    }

    private static void deleteNoteById(String url, String user, String password, long id) {

        String sql = "DELETE FROM learning_notes WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("Attempting to delete note with id = " + id);

            // 1. Bind the id parameter
            ps.setLong(1, id);

            // 2. Execute delete
            int affectedRows = ps.executeUpdate();
            System.out.println("Rows affected by DELETE: " + affectedRows);

            if (affectedRows == 0) {
                System.out.println("No note found with id = " + id + ". Nothing was deleted.");
            } else if (affectedRows == 1) {
                System.out.println("Successfully deleted note with id = " + id);
            } else {
                System.out.println("Warning: more than one row was deleted (" + affectedRows + "). Check your WHERE clause!");
            }

        } catch (SQLException e) {
            System.out.println("Error while deleting note!");
            System.out.println("Message: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
        }

        System.out.println("Delete operation finished for id = " + id);
        System.out.println("--------------------------------------------------");
    }
}
