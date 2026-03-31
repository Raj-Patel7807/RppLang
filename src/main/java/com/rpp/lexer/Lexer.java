package com.rpp.lexer;

import java.util.List;
import java.util.ArrayList;

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
                    tokens.add(new Token(TokenType.LET, word));
                } else if(word.equals("print")) {
                    tokens.add(new Token(TokenType.PRINT, word));
                } else if(word.equals("true") || word.equals("false")) {
                    tokens.add(new Token(TokenType.BOOLEAN, word));
                } else if(word.equals("if")) {
                    tokens.add(new Token(TokenType.IF, word));
                } else if(word.equals("else")) {
                    tokens.add(new Token(TokenType.ELSE, word));
                } else {
                    tokens.add(new Token(TokenType.IDENTIFIER, word));
                }
                continue;
            }

            if(Character.isDigit(cur)) {
                tokens.add(new Token(TokenType.NUMBER, readNumber()));
                continue;
            }

            if (cur == '"') {
                tokens.add(new Token(TokenType.STRING, readString()));
                continue;
            }

            if(cur == '+') {
                tokens.add(new Token(TokenType.PLUS, "+"));
            } else if(cur == '-') {
                tokens.add(new Token(TokenType.MINUS, "-"));
            } else if(cur == '*') {
                tokens.add(new Token(TokenType.MULTIPLY, "*"));
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
                        throw new RuntimeException("Unterminated comment");
                    }
                    pos += 2;
                    continue;
                } else {
                    tokens.add(new Token(TokenType.DIVIDE, "/"));
                }
            } else if(cur == '=') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.EQUAL_EQUAL, "=="));
                    pos++;
                } else {
                    tokens.add(new Token(TokenType.ASSIGN, "="));
                }
            } else if(cur == ';') {
                tokens.add(new Token(TokenType.SEMICOLON, ";"));
            } else if(cur == '(') {
                tokens.add(new Token(TokenType.LEFT_PAREN, "("));
            } else if(cur == ')') {
                tokens.add(new Token(TokenType.RIGHT_PAREN, ")"));
            } else if(cur == '{') {
                tokens.add(new Token(TokenType.LEFT_BRACE, "{"));
            } else if(cur == '}') {
                tokens.add(new Token(TokenType.RIGHT_BRACE, "}"));
            } else if(cur == '>') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.GREATER_EQUAL, ">="));
                    pos++;
                } else {
                    tokens.add(new Token(TokenType.GREATER, ">"));
                }
            } else if(cur == '<') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.LESS_EQUAL, "<="));
                    pos++;
                } else {
                    tokens.add(new Token(TokenType.LESS, "<"));
                }
            } else if(cur == '!') {
                if(pos + 1 < input.length() && input.charAt(pos + 1) == '=') {
                    tokens.add(new Token(TokenType.NOT_EQUAL, "!="));
                    pos++;
                } else {
                    throw new RuntimeException("Unexpected !");
                }
            } else {
                throw new RuntimeException("Syntax Error: " + cur);
            }

            pos++;
        }

        tokens.add(new Token(TokenType.EOF, ""));
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
            throw new RuntimeException("Unterminated string");
        }

        pos++;

        return sb.toString();
    }
}
