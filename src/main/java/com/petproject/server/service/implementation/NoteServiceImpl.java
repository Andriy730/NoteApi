package com.petproject.server.service.implementation;

import com.petproject.server.error.NoteNotFoundException;
import com.petproject.server.model.Note;
import com.petproject.server.repository.NoteRepository;
import com.petproject.server.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note addNote(Note note) {
        note.setCreated(new Date());
        note.setUpdated(new Date());
        return noteRepository.saveAndFlush(note);
    }

    @Override
    public void delete(Long id) {
        noteRepository.delete(noteRepository.findById(id).orElseThrow(() -> {
            throw new NoteNotFoundException(id);
        }));
    }

    @Override
    public Note editNote(Note note) {
        if(note.getUpdated() == null) {
            note.setUpdated(new Date());
        }
        if(note.getCreated() == null) {
            note.setCreated(this.getById(note.getId()).getCreated());
        }
        return noteRepository.saveAndFlush(note);
    }

    @Override
    public Note getById(long id) {
        return noteRepository.findById(id).orElseThrow(() -> {
            throw new NoteNotFoundException(id);
        });
    }

    @Override
    public List<Note> getAll() {
        return noteRepository.findAll();
    }
}
