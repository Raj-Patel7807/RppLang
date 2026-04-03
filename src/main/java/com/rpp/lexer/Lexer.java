package com.rpp.lexer;

import com.rpp.error.LexerError;

import java.util.List;
import java.util.ArrayList;
import java.util.function.ToLongBiFunction;

public class Lexer {
    private final String input;
    private int pos = 0;

    public Lexer(String input) {
        this.input = input;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while(pos < input.length()) {
            char cur = input.charAt(pos);

            if(Character.isWhitespace(cur)) {
                pos++;
                continue;
            }

            if(Character.isLetter(cur)) {
                String word = readWord();

                if(word.equals("let")) {
                    tokens.add(new Token(TokenType.LET, word, pos));
                } else if(word.equals("print")) {
                    tokens.add(new Token(TokenType.PRINT, word, pos));
                } else if(word.equals("true") || word.equals("false")) {
                    tokens.add(new Token(TokenType.BOOLEAN, word, pos));
                } else if(word.equals("if")) {
                    tokens.add(new Token(TokenType.IF, word, pos));
                } else if(word.equals("else")) {
                    tokens.add(new Token(TokenType.ELSE, word, pos));
                } else if(word.equals("for")) {
                    tokens.add(new Token(TokenType.FOR, word, pos));
                } else if(word.equals("while")) {
                    tokens.add(new Token(TokenType.WHILE, word, pos));
                } else if(word.equals("do")) {
                    tokens.add(new Token(TokenType.DO, word, pos));
                } else if(word.equals("break")) {
                    tokens.add(new Token(TokenType.BREAK, word, pos));
                } else if(word.equals("continue")) {
                    tokens.add(new Token(TokenType.CONTINUE, word, pos));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, word, pos));
                }
                continue;
            }

            if(Character.isDigit(cur)) {
                tokens.add(new Token(TokenType.NUMBER, readNumber(), pos));
                continue;
            }

            if(cur == '"') {
                tokens.add(new Token(TokenType.STRING, readString(), pos));
                continue;
            }

            if(cur == '+') {
                tokens.add(new Token(TokenType.PLUS, "+", pos));
            } else if(cur == '-') {
                tokens.add(new Token(TokenType.MINUS, "-", pos));
            } else if(cur == '*') {
                tokens.add(new Token(TokenType.MULTIPLY, "*", pos));
            } else if(cur == '/') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '/') {
                    pos += 2;

                    while(pos < input.length() && input.charAt(pos) != '\n') {
                        pos++;
                    }
                    continue;
                } else if(pos + 1 < input.length() && input.charAt(pos + 1) == '*') {
                    pos += 2;

                    while(pos + 1 < input.length() && !(input.charAt(pos) == '*' && input.charAt(pos + 1) == '/')) {
                        pos++;
                    }
                    if(pos + 1 >= input.length()) {
                        throw new LexerError("Unterminated comment", pos);
                    }
                    pos += 2;
                    continue;
                } else {
                    tokens.add(new Token(TokenType.DIVIDE, "/", pos));
                }
            } else if(cur == '=') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.EQUAL_EQUAL, "==", pos));
                    pos++;
                } else {
                    tokens.add(new Token(TokenType.ASSIGN, "=", pos));
                }
            } else if(cur == ';') {
                tokens.add(new Token(TokenType.SEMICOLON, ";", pos));
            } else if(cur == '(') {
                tokens.add(new Token(TokenType.LEFT_PAREN, "(", pos));
            } else if(cur == ')') {
                tokens.add(new Token(TokenType.RIGHT_PAREN, ")", pos));
            } else if(cur == '{') {
                tokens.add(new Token(TokenType.LEFT_BRACE, "{", pos));
            } else if(cur == '}') {
                tokens.add(new Token(TokenType.RIGHT_BRACE, "}", pos));
            } else if(cur == '>') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.GREATER_EQUAL, ">=", pos));
                    pos++;
                } else {
                    tokens.add(new Token(TokenType.GREATER, ">", pos));
                }
            } else if(cur == '<') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.LESS_EQUAL, "<=", pos));
                    pos++;
                } else {
                    tokens.add(new Token(TokenType.LESS, "<", pos));
                }
            } else if(cur == '!') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.NOT_EQUAL, "!=", pos));
                    pos++;
                } else {
                    throw new LexerError("Unexpected '!'", pos);
                }
            } else {
                throw new LexerError("Unexpected character: " + cur, pos);
            }

            pos++;
        }

        tokens.add(new Token(TokenType.EOF, "", pos));
        return tokens;
    }

    private String readWord() {
        StringBuilder sb = new StringBuilder();
        while(pos < input.length() && Character.isLetterOrDigit(input.charAt(pos))) {
            sb.append(input.charAt(pos++));
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
            pos++;
        }
        return sb.toString();
    }

    private String readString() {
        pos++;

        StringBuilder sb = new StringBuilder();

        while(pos < input.length() && input.charAt(pos) != '"') {
            sb.append(input.charAt(pos++));
        }

        if(pos >= input.length()) {
            throw new LexerError("Unterminated string", pos);
        }

        pos++;

        return sb.toString();
    }
}
