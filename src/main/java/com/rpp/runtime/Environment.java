package com.rpp.runtime;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Object> variables = new HashMap<>();

    public void set(String name, Object value) {
        variables.put(name, value);
    }

    public Object get(String name) {
        if(!variables.containsKey(name)) {
            throw new RuntimeException("Undefined variable: " + name);
        }
        return variables.get(name);
    }

    public boolean exists(String name) {
        return variables.containsKey(name);
    }
}
