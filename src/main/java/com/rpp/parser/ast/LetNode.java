package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

public class LetNode extends Node {
    private final String name;
    private final Node value;

    public LetNode(String name, Node value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object evaluate(Environment env) {
        if(value != null) {
            Object val = value.evaluate(env);
            env.set(name, val);
        } else {
            env.set(name, null);
        }
        return null;
    }
}
