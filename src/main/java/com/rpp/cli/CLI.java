package com.rpp.cli;

import com.rpp.io.FileReaderUtil;
import com.rpp.pipeline.CompilerPipeline;

public class CLI {

    public static void start(String[] args) {

        if(args.length == 0) {
            System.out.println("Usage: rpp <file.rpp>");
            return;
        }

        String filePath = args[0];

        String source = FileReaderUtil.readFile(filePath);

        CompilerPipeline pipeline = new CompilerPipeline();

        pipeline.run(source);
    }
}
