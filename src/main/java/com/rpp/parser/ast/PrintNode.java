package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

public class PrintNode extends Node {
    private final Node value;

    public PrintNode(Node value) {
        this.value = value;
    }

    @Override
    public Object evaluate(Environment env) {
        Object val = value.evaluate(env);
        System.out.println(String.valueOf(val));
        return null;
    }
}
