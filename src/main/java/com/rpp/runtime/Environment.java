package com.rpp.runtime;

import com.rpp.error.RuntimeError;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    private final Map<String, Object> variables = new HashMap<>();
    private final Environment parent;

    public Environment(Environment parent) {
        this.parent = parent;
    }

    public void define(String name, Object value) {
        variables.put(name, value);
    }

    public void set(String name, Object value) {
        if(variables.containsKey(name)) {
            variables.put(name, value);
            return;
        }

        if(parent != null && parent.exists(name)) {
            parent.set(name, value);
            return;
        }

        variables.put(name, value);
    }

    public Object get(String name) {
        if(variables.containsKey(name)) {
            return variables.get(name);
        }
        if(parent != null) {
            return parent.get(name);
        }

        throw new RuntimeError("Undefined variable: " + name);
    }

    public boolean exists(String name) {
        if(variables.containsKey(name)) {
            return true;
        }
        if(parent != null) {
            return parent.exists(name);
        }
        return false;
    }
}
