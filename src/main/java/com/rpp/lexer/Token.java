package com.rpp.lexer;

public class Token {

    private final TokenType type;
    private final String value;

    private final int line;
    private final int column;

    private final int index;

    public Token(TokenType type, String value, int line, int column, int index) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.column = column;
        this.index = index;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public int getIndex() {
        return index;
    }
}
