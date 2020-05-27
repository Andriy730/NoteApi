package com.petproject.server.service;

import com.petproject.server.model.Note;

import java.util.List;

public interface NoteService {
    Note addNote(Note note);
    void delete(Long id);
    Note editNote(Note note);
    Note getById(long id);
    List<Note> getAll();
}
