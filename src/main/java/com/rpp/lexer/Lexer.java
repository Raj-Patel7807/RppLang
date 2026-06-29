package com.rpp.lexer;

import com.rpp.error.LexerError;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String input;

    private int line = 1;
    private int column = 1;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while(pos < input.length()) {
            char cur = input.charAt(pos);

            if(Character.isWhitespace(cur)) {
                advance();
                continue;
            }

            if(Character.isLetter(cur)) {
                String word = readWord();

                if(word.equals("let")) {
                    tokens.add(new Token(TokenType.LET, word, line, column, pos));
                } else if(word.equals("print")) {
                    tokens.add(new Token(TokenType.PRINT, word, line, column, pos));
                } else if(word.equals("true") || word.equals("false")) {
                    tokens.add(new Token(TokenType.BOOLEAN, word, line, column, pos));
                } else if(word.equals("if")) {
                    tokens.add(new Token(TokenType.IF, word, line, column, pos));
                } else if(word.equals("else")) {
                    tokens.add(new Token(TokenType.ELSE, word, line, column, pos));
                } else if(word.equals("for")) {
                    tokens.add(new Token(TokenType.FOR, word, line, column, pos));
                } else if(word.equals("while")) {
                    tokens.add(new Token(TokenType.WHILE, word, line, column, pos));
                } else if(word.equals("do")) {
                    tokens.add(new Token(TokenType.DO, word, line, column, pos));
                } else if(word.equals("break")) {
                    tokens.add(new Token(TokenType.BREAK, word, line, column, pos));
                } else if(word.equals("continue")) {
                    tokens.add(new Token(TokenType.CONTINUE, word, line, column, pos));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, word, line, column, pos));
                }
                continue;
            }

            if(Character.isDigit(cur)) {
                tokens.add(new Token(TokenType.NUMBER, readNumber(), line, column, pos));
                continue;
            }

            if(cur == '"') {
                tokens.add(new Token(TokenType.STRING, readString(), line, column, pos));
                continue;
            }

            if(cur == '+') {
                tokens.add(new Token(TokenType.PLUS, "+", line, column, pos));
            } else if(cur == '-') {
                tokens.add(new Token(TokenType.MINUS, "-", line, column, pos));
            } else if(cur == '*') {
                tokens.add(new Token(TokenType.MULTIPLY, "*", line, column, pos));
            } else if(cur == '/') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '/') {
                    advance();
                    advance();

                    while(pos < input.length() && input.charAt(pos) != '\n') {
                        advance();
                    }
                    continue;
                } else if(pos + 1 < input.length() && input.charAt(pos + 1) == '*') {
                    advance();
                    advance();

                    while(pos + 1 < input.length() && !(input.charAt(pos) == '*' && input.charAt(pos + 1) == '/')) {
                        advance();
                    }
                    if(pos + 1 >= input.length()) {
                        throw new LexerError("Unterminated comment", line, column, pos);
                    }
                    advance();
                    advance();
                    continue;
                } else {
                    tokens.add(new Token(TokenType.DIVIDE, "/", line, column, pos));
                }
            } else if(cur == '=') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.EQUAL_EQUAL, "==", line, column, pos));
                    advance();
                } else {
                    tokens.add(new Token(TokenType.ASSIGN, "=", line, column, pos));
                }
            } else if(cur == ';') {
                tokens.add(new Token(TokenType.SEMICOLON, ";", line, column, pos));
            } else if(cur == '(') {
                tokens.add(new Token(TokenType.LEFT_PAREN, "(", line, column, pos));
            } else if(cur == ')') {
                tokens.add(new Token(TokenType.RIGHT_PAREN, ")", line, column, pos));
            } else if(cur == '{') {
                tokens.add(new Token(TokenType.LEFT_BRACE, "{", line, column, pos));
            } else if(cur == '}') {
                tokens.add(new Token(TokenType.RIGHT_BRACE, "}", line, column, pos));
            } else if(cur == '>') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.GREATER_EQUAL, ">=", line, column, pos));
                    advance();
                } else {
                    tokens.add(new Token(TokenType.GREATER, ">", line, column, pos));
                }
            } else if(cur == '<') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.LESS_EQUAL, "<=", line, column, pos));
                    advance();
                } else {
                    tokens.add(new Token(TokenType.LESS, "<", line, column, pos));
                }
            } else if(cur == '!') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.NOT_EQUAL, "!=", line, column, pos));
                    advance();
                } else {
                    throw new LexerError("Unexpected '!'", line, column, pos);
                }
            } else {
                throw new LexerError("Unexpected character: " + cur, line, column, pos);
            }

            advance();
        }

        tokens.add(new Token(TokenType.EOF, "", line, column, pos));
        return tokens;
    }

    private String readWord() {
        StringBuilder sb = new StringBuilder();
        while(pos < input.length() && Character.isLetterOrDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            advance();
        }
        return sb.toString();
    }

    private String readNumber() {
        StringBuilder sb = new StringBuilder();
        boolean hasDot = false;

        while(pos < input.length()) {
            char c = input.charAt(pos);

            if(Character.isDigit(c)) {
                sb.append(c);
            } else if(c == '.' && !hasDot) {
                hasDot = true;
                sb.append(c);
            } else {
                break;
            }
            advance();
        }
        return sb.toString();
    }

    private String readString() {
        advance();

        StringBuilder sb = new StringBuilder();

        while(pos < input.length() && input.charAt(pos) != '"') {
            sb.append(input.charAt(pos));
            advance();
        }

        if(pos >= input.length()) {
            throw new LexerError("Unterminated string", line, column, pos);
        }

        advance();

        return sb.toString();
    }

    private void advance() {
        if(pos >= input.length()) {
            return;
        }

        if(input.charAt(pos) == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }

        pos++;
    }
}
