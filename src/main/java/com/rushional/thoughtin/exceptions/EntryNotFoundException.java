package com.rushional.thoughtin.exceptions;

public class EntryNotFoundException extends RuntimeException {
    public EntryNotFoundException(Long id) {
        super("Could not find an Entry with id: " + id);
    }
}
