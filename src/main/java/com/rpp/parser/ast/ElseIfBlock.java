package com.rpp.parser.ast;

public class ElseIfBlock {
    public Node condition;
    public BlockNode block;

    public ElseIfBlock(Node condition, BlockNode block) {
        this.condition = condition;
        this.block = block;
    }
}
