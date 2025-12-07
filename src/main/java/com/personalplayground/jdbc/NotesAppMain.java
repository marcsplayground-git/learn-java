package com.personalplayground.jdbc;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class NotesAppMain {

    public static void main(String[] args) {
        System.out.println("Starting NotesAppMain...");

        // 1. Get pooled DataSource
        DataSource ds = ConnectionPool.getDataSource();

        // 2. Create DAO
        NoteDao noteDao = new JdbcNoteDao(ds);

        // 3. Create a new note
        Note newNote = new Note("NotesApp test note", "This note is managed via JdbcNoteDao.");
        Note created = noteDao.create(newNote);

        System.out.println("Created note: " + created);

        // 4. List all notes
        System.out.println("All notes after creation:");
        printNotes(noteDao.findAll());

        // 5. Update the note
        created.setTitle("Updated NotesApp title");
        created.setBody("Updated body from NotesAppMain.");
        boolean updated = noteDao.update(created);
        System.out.println("Update result: " + updated);

        // 6. Load by id and print
        Optional<Note> loaded = noteDao.findById(created.getId());
        System.out.println("Loaded by id: " + loaded.orElse(null));

        // 7. Delete the note
        boolean deleted = noteDao.deleteById(created.getId());
        System.out.println("Delete result: " + deleted);

        // 8. List all notes again
        System.out.println("All notes after delete:");
        printNotes(noteDao.findAll());

        System.out.println("NotesAppMain finished.");
    }

    private static void printNotes(List<Note> notes) {
        if (notes.isEmpty()) {
            System.out.println("  [no notes found]");
            return;
        }

        for (Note n : notes) {
            System.out.println("  " + n);
        }
    }
}
