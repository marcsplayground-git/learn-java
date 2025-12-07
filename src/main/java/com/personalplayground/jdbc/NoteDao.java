package com.personalplayground.jdbc;

import java.util.List;
import java.util.Optional;

public interface NoteDao {

    Note create(Note note);

    Optional<Note> findById(long id);

    List<Note> findAll();

    boolean update(Note note);

    boolean deleteById(long id);
}
