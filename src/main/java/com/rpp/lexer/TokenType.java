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

    GREATER,
    GREATER_EQUAL,
    LESS,
    LESS_EQUAL,
    EQUAL_EQUAL,
    NOT_EQUAL,

    SEMICOLON,

    LEFT_PAREN,
    RIGHT_PAREN,

    LEFT_BRACE,
    RIGHT_BRACE,

    IF,
    ELSE,

    FOR,
    WHILE,
    DO,

    BREAK,
    CONTINUE,

    EOF
}
