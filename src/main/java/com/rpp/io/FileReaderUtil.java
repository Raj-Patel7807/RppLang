package com.rpp.io;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderUtil {

    public static String readFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch(Exception e) {
            throw new RuntimeException("Failed to read file: " + path);
        }
    }
}
