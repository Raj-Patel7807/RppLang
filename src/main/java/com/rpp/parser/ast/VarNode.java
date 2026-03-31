package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

public class VarNode extends Node {
    private final String name;

    public VarNode(String name) {
        this.name = name;
    }

    @Override
    public Object evaluate(Environment env) {
        return env.get(name);
    }
}
