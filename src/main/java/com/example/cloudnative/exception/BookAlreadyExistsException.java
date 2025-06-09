package com.example.cloudnative.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String message) {
        super("A Book with this title already exists: " + message);
    }
}
