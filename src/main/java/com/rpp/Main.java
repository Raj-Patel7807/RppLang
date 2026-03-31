package com.rpp;

import com.rpp.lexer.Lexer;
import com.rpp.parser.Parser;
import com.rpp.parser.ast.Node;
import com.rpp.runtime.Environment;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

        List<Node> nodes = parser.parse();
        Environment env = new Environment();

        for(Node node : nodes) {
            node.evaluate(env);
        }
    }
}
