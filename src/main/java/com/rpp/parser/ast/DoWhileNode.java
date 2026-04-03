package com.rpp.parser.ast;

import com.rpp.runtime.Environment;
import com.rpp.runtime.exception.BreakException;
import com.rpp.runtime.exception.ContinueException;

public class DoWhileNode extends Node {
    private final Node condition;
    private final BlockNode body;

    public DoWhileNode(Node condition, BlockNode body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public Object evaluate(Environment env) {
        Object result = null;

        do {
            try {
                result = body.evaluate(env);
            } catch(ContinueException e) {

            } catch(BreakException e) {
                break;
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
