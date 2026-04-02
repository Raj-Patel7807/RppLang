package com.rpp.error;

public class ParserError extends RuntimeException {
    public ParserError(String message) {
        super("Parser Error: " + message);
    }
}
