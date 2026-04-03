package com.rpp.parser.ast;

import com.rpp.error.RuntimeError;
import com.rpp.runtime.Environment;

import java.util.List;

public class IfNode extends Node {
    private final Node condition;
    private final BlockNode thenBlock;
    private final List<ElseIfBlock> elseIfs;
    private final BlockNode elseBlock;

    public IfNode(Node condition, BlockNode thenBlock, List<ElseIfBlock> elseIfs, BlockNode elseBlock) {
        this.condition = condition;
        this.thenBlock = thenBlock;
        this.elseIfs = elseIfs;
        this.elseBlock = elseBlock;
    }

    @Override
    public Object evaluate(Environment env) {
        Object cond = condition.evaluate(env);

        if(!(cond instanceof Boolean)) {
            throw new RuntimeError("Condition must be boolean");
        }

        if((boolean)cond) {
            return thenBlock.evaluate(env);
        }

        for(ElseIfBlock e : elseIfs) {
            Object elseifCond = e.condition.evaluate(env);

            if(!(elseifCond instanceof Boolean)) {
                throw new RuntimeError("Condition must be boolean");
            }

            if((boolean) elseifCond) {
                return e.block.evaluate(env);
            }
        }

        if(elseBlock != null) {
            return elseBlock.evaluate(env);
        }

        return null;
    }
}
