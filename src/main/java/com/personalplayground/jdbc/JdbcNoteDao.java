package com.personalplayground.jdbc;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcNoteDao implements NoteDao {

    private final DataSource dataSource;

    private static final String UPDATE_SQL =
            "UPDATE learning_notes SET title = ?, body = ? WHERE id = ?";

    public JdbcNoteDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Note create(Note note) {
        String sql = "INSERT INTO learning_notes (title, body) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, note.getTitle());
            ps.setString(2, note.getBody());

            int affected = ps.executeUpdate();
            if (affected != 1) {
                throw new SQLException("Expected 1 row to be inserted, got " + affected);
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long id = keys.getLong(1);
                    note.setId(id);

                    // Retrieve created_at from DB if you want full object
                    // Optional: re-load the note via findById(id) instead
                }
            }

            return note;

        } catch (SQLException e) {
            System.out.println("Error in JdbcNoteDao.create");
            JdbcUtils.printSQLException(e);
            throw new RuntimeException("Failed to create note", e);
        }
    }

    @Override
    public Optional<Note> findById(long id) {
        String sql = "SELECT id, title, body, created_at FROM learning_notes WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToNote(rs));
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error in JdbcNoteDao.findById");
            JdbcUtils.printSQLException(e);
            throw new RuntimeException("Failed to find note by id", e);
        }
    }

    @Override
    public List<Note> findAll() {
        String sql = "SELECT id, title, body, created_at " +
                "FROM learning_notes " +
                "ORDER BY id";

        List<Note> notes = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                notes.add(mapRowToNote(rs));
            }

            return notes;

        } catch (SQLException e) {
            System.out.println("Error in JdbcNoteDao.findAll");
            JdbcUtils.printSQLException(e);
            throw new RuntimeException("Failed to find all notes", e);
        }
    }

    @Override
    public boolean update(Note note) {
        if (note.getId() == null) {
            throw new IllegalArgumentException("Note id must not be null for update");
        }

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, note.getTitle());
            ps.setString(2, note.getBody());
            ps.setLong(3, note.getId());

            int affected = ps.executeUpdate();
            return affected == 1;

        } catch (SQLException e) {
            System.out.println("Error in JdbcNoteDao.update");
            JdbcUtils.printSQLException(e);
            throw new RuntimeException("Failed to update note", e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        String sql = "DELETE FROM learning_notes WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int affected = ps.executeUpdate();
            return affected == 1;

        } catch (SQLException e) {
            System.out.println("Error in JdbcNoteDao.deleteById");
            JdbcUtils.printSQLException(e);
            throw new RuntimeException("Failed to delete note", e);
        }
    }

    private Note mapRowToNote(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String title = rs.getString("title");
        String body = rs.getString("body");

        Timestamp ts = rs.getTimestamp("created_at");
        LocalDateTime createdAt = ts != null ? ts.toLocalDateTime() : null;

        return new Note(id, title, body, createdAt);
    }
}
