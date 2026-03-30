package com.rpp.runtime;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Integer> variables = new HashMap<>();

    public void set(String name, int value) {
        variables.put(name, value);
    }

    public int get(String name) {
        if(!variables.containsKey(name)) {
            throw new RuntimeException("Undefined variable: " + name);
        }
        return variables.get(name);
    }
}
