package com.rpp.error;

public class ParserError extends RuntimeException {

    public ParserError(String message, int line, int column, int position) {
        super("Lexer Error\n" + "Line : " + line + "\n" + "Column : " + column + "\n\n" + "Position: " + position + "\n\n" + message);
    }
    
}
