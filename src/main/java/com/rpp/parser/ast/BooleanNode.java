package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

public class BooleanNode extends Node {
    private final boolean value;

    public BooleanNode(boolean value) {
        this.value = value;
    }

    @Override
    public Object evaluate(Environment env) {
        return value;
    }
}
