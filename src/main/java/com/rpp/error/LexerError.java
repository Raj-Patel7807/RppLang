package com.rpp.error;

public class LexerError extends RuntimeException {
    public LexerError(String message, int position) {
        super("Lexer Error at Position " + position + ": " + message);
    }
}
