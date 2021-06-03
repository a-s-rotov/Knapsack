package com.mobiquity.exception;

public class FileParseException extends RuntimeException {
    public FileParseException(String message, Exception e) {
        super(message, e);
    }

    public FileParseException(String message) {
        super(message);
    }
}
