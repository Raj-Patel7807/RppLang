package com.rpp.parser;

import com.rpp.lexer.TokenType;
import com.rpp.runtime.Environment;
import com.rpp.lexer.Token;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int pos = 0;
    private final Environment env = new Environment();

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void parse() {
        while(!check(TokenType.EOF)) {
            statement();
        }
    }

    private void statement() {
        if(match(TokenType.LET)) {
            String name = consume(TokenType.IDENTIFIER).value;
            consume(TokenType.ASSIGN);

            int value = expression();

            consume(TokenType.SEMICOLON);
            env.set(name, value);

        } else if(match(TokenType.PRINT)) {
            consume(TokenType.LEFT_PAREN);

            Object value = printValue();

            consume(TokenType.RIGHT_PAREN);
            consume(TokenType.SEMICOLON);

            System.out.println(value);

        } else {
            throw new RuntimeException("Invalid statement at token: " + peek());
        }
    }

    private Object printValue() {
        if(check(TokenType.STRING)) {
            return consume(TokenType.STRING).value;
        }
        return expression();
    }

    private int expression() {
        int left = term();

        while(match(TokenType.PLUS) || match(TokenType.MINUS)) {
            Token operator = tokens.get(pos - 1);
            int right = term();

            if(operator.type == TokenType.PLUS) {
                left += right;
            } else {
                left -= right;
            }
        }

        return left;
    }

    private int term() {
        int left = factor();

        while(match(TokenType.MULTIPLY) || match(TokenType.DIVIDE)) {
            Token operator = tokens.get(pos - 1);
            int right = factor();

            if(operator.type == TokenType.MULTIPLY) {
                left *= right;
            } else {
                left /= right;
            }
        }

        return left;
    }

    private int factor() {
        if(match(TokenType.NUMBER)) {
            return Integer.parseInt(tokens.get(pos - 1).value);
        }

        if(match(TokenType.IDENTIFIER)) {
            return env.get(tokens.get(pos - 1).value);
        }

        if(match(TokenType.LEFT_PAREN)) {
            int value = expression();
            consume(TokenType.RIGHT_PAREN);
            return value;
        }

        throw new RuntimeException("Invalid expression at token: " + peek());
    }

    private boolean match(TokenType type) {
        if(check(type)) {
            pos++;
            return true;
        }
        return false;
    }

    private Token consume(TokenType type) {
        if(check(type)) {
            return tokens.get(pos++);
        }
        throw new RuntimeException("Expected " + type + " but got " + peek().type);
    }

    private boolean check(TokenType type) {
        return tokens.get(pos).type == type;
    }

    private Token peek() {
        return tokens.get(pos);
    }
}
