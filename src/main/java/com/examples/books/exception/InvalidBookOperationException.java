package com.examples.books.exception;

public class InvalidBookOperationException extends RuntimeException {

    public InvalidBookOperationException(String message) {
        super(message);
    }
}
