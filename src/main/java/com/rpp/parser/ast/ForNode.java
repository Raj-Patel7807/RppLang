package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

import java.util.List;

public class ForNode extends Node {
    private final Node init;
    private final Node condition;
    private final Node update;
    private final List<Node> body;

    public ForNode(Node init, Node condition, Node update, List<Node> body) {
        this.init = init;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Override
    public Object evaluate(Environment env) {
        Object result = null;

        if(init != null) init.evaluate(env);

        while(condition == null || isTrue(condition.evaluate(env))) {

            for(Node stmt : body) {
                result = stmt.evaluate(env);
            }

            if(update != null) {
                update.evaluate(env);
            }
        }

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
