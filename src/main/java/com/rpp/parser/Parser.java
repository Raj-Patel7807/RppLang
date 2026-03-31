package com.rpp.parser;

import com.rpp.lexer.TokenType;
import com.rpp.parser.ast.*;
import com.rpp.lexer.Token;

import java.util.List;
import java.util.ArrayList;

public class Parser {
    private final List<Token> tokens;
    private int pos = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Node> parse() {
        List<Node> nodes = new ArrayList<>();

        while(!check(TokenType.EOF)) {
            nodes.add(statement());
        }

        return nodes;
    }

    private Node statement() {
        if(match(TokenType.LET)) {
            String name = consume(TokenType.IDENTIFIER).value;
            Node value = null;

            if(match(TokenType.ASSIGN)) {
                value = expression();
            }

            consume(TokenType.SEMICOLON);

            return new LetNode(name, value);

        } else if(match(TokenType.PRINT)) {
            consume(TokenType.LEFT_PAREN);

            Node value = expression();

            consume(TokenType.RIGHT_PAREN);
            consume(TokenType.SEMICOLON);

            return new PrintNode(value);
        }

        throw new RuntimeException("Invalid statement at token: " + peek());
    }

    private Node expression() {
        Node left = term();

        while(match(TokenType.PLUS) || match(TokenType.MINUS)) {
            Token operator = tokens.get(pos - 1);
            Node right = term();
            left = new BinaryOpNode(left, operator.type, right);
        }

        return left;
    }

    private Node term() {
        Node left = factor();

        while(match(TokenType.MULTIPLY) || match(TokenType.DIVIDE)) {
            Token operator = tokens.get(pos - 1);
            Node right = factor();
            left = new BinaryOpNode(left, operator.type, right);
        }

        return left;
    }

    private Node factor() {
        if(match(TokenType.NUMBER)) {
            return new NumberNode(tokens.get(pos - 1).value);
        }
        if(match(TokenType.STRING)) {
            return new StringNode(tokens.get(pos - 1).value);
        }
        if(match(TokenType.BOOLEAN)) {
            return new BooleanNode(Boolean.parseBoolean(tokens.get(pos - 1).value));
        }
        if(match(TokenType.IDENTIFIER)) {
            return new VarNode(tokens.get(pos - 1).value);
        }
        if(match(TokenType.LEFT_PAREN)) {
            Node node = expression();
            consume(TokenType.RIGHT_PAREN);
            return node;
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
