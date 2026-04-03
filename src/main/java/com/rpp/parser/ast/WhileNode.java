package com.rpp.parser.ast;

import com.rpp.runtime.Environment;
import com.rpp.runtime.exception.BreakException;
import com.rpp.runtime.exception.ContinueException;

public class WhileNode extends Node {
    private final Node condition;
    private final BlockNode body;

    public WhileNode(Node condition, BlockNode body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Object evaluate(Environment env) {
        Object result = null;

        while(isTrue(condition.evaluate(env))) {
            try {
                result = body.evaluate(env);
            } catch(ContinueException e) {
                continue;
            } catch(BreakException e) {
                break;
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
