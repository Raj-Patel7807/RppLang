package com.rpp.error;

public class RuntimeError extends RuntimeException {
    public RuntimeError(String message) {
        super("Runtime Error: " + message);
    }
}
