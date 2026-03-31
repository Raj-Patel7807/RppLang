package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

public class StringNode extends Node {
    private final String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public Object evaluate(Environment env) {
        return value;
    }
}
