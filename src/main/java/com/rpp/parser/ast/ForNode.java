package com.rpp.parser.ast;

import com.rpp.runtime.Environment;
import com.rpp.runtime.exception.BreakException;
import com.rpp.runtime.exception.ContinueException;

public class ForNode extends Node {
    private final Node init;
    private final Node condition;
    private final Node update;
    private final BlockNode body;

    public ForNode(Node init, Node condition, Node update, BlockNode body) {
        this.init = init;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    @Override
    public Object evaluate(Environment env) {
        Environment local = new Environment(env);

        Object result = null;

        if(init != null) init.evaluate(local);

        while(condition == null || isTrue(condition.evaluate(local))) {

            try {
                result = body.evaluate(local);
            } catch(ContinueException e) {

            } catch(BreakException e) {
                break;
            }

            if(update != null) {
                update.evaluate(local);
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
