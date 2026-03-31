package com.rpp.parser.ast;

import com.rpp.runtime.Environment;

public abstract class Node {
    public abstract Object evaluate(Environment env);
}
