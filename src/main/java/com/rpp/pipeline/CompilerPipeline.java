package com.rpp.pipeline;

import com.rpp.lexer.Lexer;
import com.rpp.lexer.Token;
import com.rpp.parser.Parser;
import com.rpp.parser.ast.Node;
import com.rpp.runtime.Environment;

import java.util.List;

public class CompilerPipeline {
    public void run(String source) {
        try {
            Lexer lexer = new Lexer(source);
            List<Token> tokens = lexer.tokenize();

            Parser parser = new Parser(tokens);
            List<Node> program = parser.parse();

            Environment env = new Environment(null);

            for(Node stmt : program) {
                stmt.evaluate(env);
            }

        } catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
