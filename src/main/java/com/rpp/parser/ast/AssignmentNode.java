package com.rpp.parser.ast;

import com.rpp.error.RuntimeError;
import com.rpp.runtime.Environment;

public class AssignmentNode extends Node {
    private final String name;
    private final Node value;

    public AssignmentNode(String name, Node value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object evaluate(Environment env) {
        Object val = value.evaluate(env);

        if(!env.exists(name)) {
            throw new RuntimeError("Variable not declared: " + name);
        }

        env.set(name, val);
        return val;
    }
}
