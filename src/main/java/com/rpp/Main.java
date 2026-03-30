package com.rpp;

import com.rpp.lexer.Lexer;
import com.rpp.parser.Parser;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {

        if(args.length == 0) {
            System.out.println("Usage: java com.rpp.Main <file.rpp>");
            return;
        }

        String filePath = args[0];

        if(!filePath.endsWith(".rpp")) {
            System.out.println("Error: Only .rpp files allowed");
            return;
        }

        String code = new String(
                Files.readAllBytes(Paths.get(filePath))
        );

        Lexer lexer = new Lexer(code);

        Parser parser = new Parser(lexer.tokenize());

        parser.parse();
    }
}
