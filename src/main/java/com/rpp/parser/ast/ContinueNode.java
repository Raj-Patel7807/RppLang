package com.rpp.parser.ast;

import com.rpp.runtime.Environment;
import com.rpp.runtime.exception.ContinueException;

public class ContinueNode extends Node {

    @Override
    public Object evaluate(Environment env) {
        throw new ContinueException();
    }
}
