package com.rpp.parser.ast;

import com.rpp.lexer.Token;
import com.rpp.lexer.TokenType;
import com.rpp.runtime.Environment;

public class BinaryOpNode extends Node {
    private final Node left;
    private final Node right;
    private final TokenType operator;

    public BinaryOpNode(Node left, TokenType operator, Node right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Object evaluate(Environment env) {
        Object l = left.evaluate(env);
        Object r = right.evaluate(env);

        if(operator == TokenType.PLUS) {
            if(l instanceof String || r instanceof String) {
                return String.valueOf(l) + String.valueOf(r);
            }
        }

        if(l instanceof Double || r instanceof Double) {
            double a = ((Number)l).doubleValue();
            double b = ((Number)r).doubleValue();

            if(operator == TokenType.PLUS) {
                return a + b;
            } else if(operator == TokenType.MINUS) {
                return a - b;
            } else if(operator == TokenType.MULTIPLY) {
                return a * b;
            } else if(operator == TokenType.DIVIDE) {
                return a / b;
            } else if(operator == TokenType.GREATER) {
                return a > b;
            } else if(operator == TokenType.GREATER_EQUAL) {
                return a >= b;
            } else if(operator == TokenType.LESS) {
                return a < b;
            } else if(operator == TokenType.LESS_EQUAL) {
                return a <= b;
            } else if(operator == TokenType.EQUAL_EQUAL) {
                return a == b;
            } else if(operator == TokenType.NOT_EQUAL) {
                return a != b;
            } else {
                throw new RuntimeException("Invalid operator for Double");
            }
        }

        if(l instanceof java.math.BigInteger || r instanceof java.math.BigInteger) {
            java.math.BigInteger a = new java.math.BigInteger(l.toString());
            java.math.BigInteger b = new java.math.BigInteger(r.toString());

            if(operator == TokenType.PLUS) {
                return a.add(b);
            } else if(operator == TokenType.MINUS) {
                return a.subtract(b);
            } else if(operator == TokenType.MULTIPLY) {
                return a.multiply(b);
            } else if(operator == TokenType.DIVIDE) {
                return a.divide(b);
            } else if(operator == TokenType.GREATER) {
                return a.compareTo(b) > 0;
            } else if(operator == TokenType.GREATER_EQUAL) {
                return a.compareTo(b) >= 0;
            } else if(operator == TokenType.LESS) {
                return a.compareTo(b) < 0;
            } else if(operator == TokenType.LESS_EQUAL) {
                return a.compareTo(b) <= 0;
            } else if(operator == TokenType.EQUAL_EQUAL) {
                return a.compareTo(b) == 0;
            } else if(operator == TokenType.NOT_EQUAL) {
                return a.compareTo(b) != 0;
            } else {
                throw new RuntimeException("Invalid operator for BigInteger");
            }
        }

        if(l instanceof Long || r instanceof Long) {
            long a = ((Number)l).longValue();
            long b = ((Number)r).longValue();

            if(operator == TokenType.PLUS) {
                return a + b;
            } else if(operator == TokenType.MINUS) {
                return a - b;
            } else if(operator == TokenType.MULTIPLY) {
                return a * b;
            } else if(operator == TokenType.DIVIDE) {
                return a / b;
            } else if(operator == TokenType.GREATER) {
                return a > b;
            } else if(operator == TokenType.GREATER_EQUAL) {
                return a >= b;
            } else if(operator == TokenType.LESS) {
                return a < b;
            } else if(operator == TokenType.LESS_EQUAL) {
                return a <= b;
            } else if(operator == TokenType.EQUAL_EQUAL) {
                return a == b;
            } else if(operator == TokenType.NOT_EQUAL) {
                return a != b;
            } else {
                throw new RuntimeException("Invalid operator for Long");
            }
        }

        if(l instanceof Integer && r instanceof Integer) {
            int a = (int) l;
            int b = (int) r;

            if(operator == TokenType.PLUS) {
                return a + b;
            } else if(operator == TokenType.MINUS) {
                return a - b;
            } else if(operator == TokenType.MULTIPLY) {
                return a * b;
            } else if(operator == TokenType.DIVIDE) {
                return a / b;
            } else if(operator == TokenType.GREATER) {
                return a > b;
            } else if(operator == TokenType.GREATER_EQUAL) {
                return a >= b;
            } else if(operator == TokenType.LESS) {
                return a < b;
            } else if(operator == TokenType.LESS_EQUAL) {
                return a <= b;
            } else if(operator == TokenType.EQUAL_EQUAL) {
                return a == b;
            } else if(operator == TokenType.NOT_EQUAL) {
                return a != b;
            } else {
                throw new RuntimeException("Invalid operator for Integer");
            }
        }

        if(operator == TokenType.EQUAL_EQUAL) {
            return l.equals(r);
        } else if(operator == TokenType.NOT_EQUAL) {
            return !l.equals(r);
        }

        throw new RuntimeException("Unsupported types: " + l + " , " + r);
    }
}
