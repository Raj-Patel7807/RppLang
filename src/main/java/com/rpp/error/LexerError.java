package com.rpp.error;

public class LexerError extends RuntimeException {

    public LexerError(String message, int line, int column, int position) {
        super("Lexer Error\n" + "Line : " + line + "\n" + "Column : " + column + "\n\n" + "Position: " + position + "\n\n" + message);
    }
    
}
