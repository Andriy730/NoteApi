package com.petproject.server.error;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(long id){
        super("Note id not found: " + id);
    }
}
