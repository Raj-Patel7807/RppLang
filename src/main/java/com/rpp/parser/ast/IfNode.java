package com.rpp.parser.ast;

import com.rpp.error.RuntimeError;
import com.rpp.runtime.Environment;

import java.util.List;

public class IfNode extends Node {
    private final Node condition;
    private final List<Node> thenBlock;
    private final List<ElseIfBlock> elseIfs;
    private final List<Node> elseBlock;

    public IfNode(Node condition, List<Node> thenBlock, List<ElseIfBlock> elseIfs, List<Node> elseBlock) {
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
            for(Node stmt : thenBlock) stmt.evaluate(env);
            return null;
        }

        for(ElseIfBlock e : elseIfs) {
            if((boolean) e.condition.evaluate(env)) {
                for(Node stmt : e.block) {
                    stmt.evaluate(env);
                }
                return null;
            }
        }

        if(elseBlock != null) {
            for(Node stmt : elseBlock) {
                stmt.evaluate(env);
            }
        }

        return null;
    }
}
