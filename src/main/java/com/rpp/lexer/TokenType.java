package com.rpp.lexer;

public enum TokenType {
    IDENTIFIER,
    NUMBER,

    STRING,
    BOOLEAN,

    LET,
    PRINT,

    ASSIGN,

    EQUAL,
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,

    SEMICOLON,

    LEFT_PAREN,
    RIGHT_PAREN,

    EOF
}
