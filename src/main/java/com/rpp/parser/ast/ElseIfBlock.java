package com.rpp.parser.ast;

import java.util.List;

public class ElseIfBlock {
    public Node condition;
    public List<Node> block;

    public ElseIfBlock(Node condition, List<Node> block) {
        this.condition = condition;
        this.block = block;
    }
}
