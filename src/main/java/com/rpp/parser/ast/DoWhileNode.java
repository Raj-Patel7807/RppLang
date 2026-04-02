package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

import java.util.List;

public class DoWhileNode extends Node {
    private final Node condition;
    private final List<Node> body;

    public DoWhileNode(Node condition, List<Node> body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Object evaluate(Environment env) {
        Object result = null;

        do {
            for(Node stmt : body) {
                result = stmt.evaluate(env);
            }
        } while(isTrue(condition.evaluate(env)));

        return result;
    }

    private boolean isTrue(Object val) {
        if(val instanceof Boolean) {
            return (Boolean)val;
        }
        if(val instanceof Number) {
            return ((Number)val).doubleValue() != 0;
        }
        return val != null;
    }
}
