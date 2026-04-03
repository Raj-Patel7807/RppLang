package com.rpp.parser.ast;

import com.rpp.runtime.Environment;
import com.rpp.runtime.exception.BreakException;

public class BreakNode extends Node {

    @Override
    public Object evaluate(Environment env) {
        throw new BreakException();
    }
}
