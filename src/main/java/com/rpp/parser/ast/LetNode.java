package com.rpp.parser.ast;

import com.rpp.error.RuntimeError;
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
        if(env.exists(name)) {
            throw new RuntimeError("Variable already declared: " + name);
        }

        if(value != null) {
            Object val = value.evaluate(env);
            env.define(name, val);
        } else {
            env.define(name, null);
        }
        return null;
    }
}
