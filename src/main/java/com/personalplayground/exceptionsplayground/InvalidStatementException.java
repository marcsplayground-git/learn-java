package com.personalplayground.exceptionsplayground;

public class InvalidStatementException extends Exception {

    // Simple constructor with only a message
    public InvalidStatementException(String message) {
        super(message);
    }

    // Constructor with message + cause (for exception chaining)
    public InvalidStatementException(String message, Throwable cause) {
        super(message, cause);
    }
}
