package com.rpp.parser;

import com.rpp.lexer.TokenType;
import com.rpp.parser.ast.*;
import com.rpp.lexer.Token;
import com.rpp.error.ParserError;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;

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

        } else if(match(TokenType.IF)) {
            consume(TokenType.LEFT_PAREN);
            Node condition = expression();
            consume(TokenType.RIGHT_PAREN);

            List<Node> thenBlock = block();
            List<ElseIfBlock> elseIfs = new ArrayList<>();
            List<Node> elseBlock = null;

            while(match(TokenType.ELSE)) {
                if(match(TokenType.IF)) {
                    consume(TokenType.LEFT_PAREN);
                    Node cond = expression();
                    consume(TokenType.RIGHT_PAREN);

                    List<Node> blk = block();
                    elseIfs.add(new ElseIfBlock(cond, new BlockNode(blk)));
                } else {
                    elseBlock = block();
                    break;
                }
            }

            return new IfNode(condition, new BlockNode(thenBlock), elseIfs, new BlockNode(elseBlock));

        } else if(match(TokenType.FOR)) {
            consume(TokenType.LEFT_PAREN);

            Node init = null;
            if(!check(TokenType.SEMICOLON)) {
                init = statement();
            }

            Node condition = null;
            if(!check(TokenType.SEMICOLON)) {
                condition = expression();
            }
            consume(TokenType.SEMICOLON);

            Node update = null;
            if(!check(TokenType.SEMICOLON)) {
                String name = consume(TokenType.IDENTIFIER).value;
                consume(TokenType.ASSIGN);

                Node value = expression();

                update = new AssignmentNode(name, value);
            }
            consume(TokenType.RIGHT_PAREN);

            List<Node> body = block();

            return new ForNode(init, condition, update, new BlockNode(body));

        } else if(match(TokenType.WHILE)) {
            consume(TokenType.LEFT_PAREN);

            Node condition = expression();

            consume(TokenType.RIGHT_PAREN);

            List<Node> body = block();

            return new WhileNode(condition, new BlockNode(body));

        } else if(match(TokenType.DO)) {
            List<Node> body = block();

            consume(TokenType.WHILE);
            consume(TokenType.LEFT_PAREN);

            Node condition = expression();

            consume(TokenType.RIGHT_PAREN);
            consume(TokenType.SEMICOLON);

            return new DoWhileNode(condition, new BlockNode(body));

        } else if(check(TokenType.IDENTIFIER) && checkNext(TokenType.ASSIGN)) {
            String name = consume(TokenType.IDENTIFIER).value;
            consume(TokenType.ASSIGN);

            Node value = expression();

            consume(TokenType.SEMICOLON);

            return new AssignmentNode(name, value);
        }

        throw new ParserError("Invalid statement at token: " + peek());
    }

    private Node expression() {
        Node left = equality();

        while(match(TokenType.PLUS) || match(TokenType.MINUS)) {
            Token op = tokens.get(pos - 1);
            Node right = equality();
            left = new BinaryOpNode(left, op.type, right);
        }

        return left;
    }

    private Node equality() {
        Node left = comparison();

        while(match(TokenType.EQUAL_EQUAL) || match(TokenType.NOT_EQUAL)) {
            Token op = tokens.get(pos - 1);
            Node right = comparison();
            left = new BinaryOpNode(left, op.type, right);
        }

        return left;
    }

    private Node comparison() {
        Node left = term();

        while(match(TokenType.GREATER) || match(TokenType.GREATER_EQUAL) || match(TokenType.LESS) || match(TokenType.LESS_EQUAL)) {
            Token op = tokens.get(pos - 1);
            Node right = term();
            left = new BinaryOpNode(left, op.type, right);
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

        throw new ParserError("Invalid expression at token: " + peek());
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
        throw new ParserError("Expected " + type + " but got " + peek().type + " at position " + peek().position);
    }

    private boolean check(TokenType type) {
        return tokens.get(pos).type == type;
    }

    private boolean checkNext(TokenType type) {
        if(pos + 1 >= tokens.size()) {
            return false;
        }
        return tokens.get(pos + 1).type == type;
    }

    private Token peek() {
        return tokens.get(pos);
    }

    private List<Node> block() {
        consume(TokenType.LEFT_BRACE);

        List<Node> states = new ArrayList<>();

        while(!check(TokenType.RIGHT_BRACE) && !check(TokenType.EOF)) {
            states.add(statement());
        }

        consume(TokenType.RIGHT_BRACE);
        return states;
    }
}
