package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

import java.math.BigInteger;

public class NumberNode extends Node {
    private final String value;

    public NumberNode(String value) {
        this.value = value;
    }

    @Override
    public Object evaluate(Environment env) {

        if(value.contains(".")) {
            return Double.parseDouble(value);
        }

        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException e1) {
            try {
                return Long.parseLong(value);
            } catch(NumberFormatException e2) {
                return new BigInteger(value);
            }
        }
    }
}
