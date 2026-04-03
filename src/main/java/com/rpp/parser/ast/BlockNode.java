package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends Node {
    private final List<Node> statements;

    public BlockNode(List<Node> statements) {
        if(statements == null) {
            this.statements = new ArrayList<>();
        } else {
            this.statements = statements;
        }
    }

    @Override
    public Object evaluate(Environment env) {
        Environment local = new Environment(env);

        Object result = null;

        for(Node stmt : statements) {
            result = stmt.evaluate(local);
        }

        return result;
    }
}
