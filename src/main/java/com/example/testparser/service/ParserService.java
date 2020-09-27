package com.example.testparser.service;

import com.example.testparser.parser.Parser;
import com.example.testparser.parser.ParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class ParserService {
    private final ParserFactory parserFactory;

    @Autowired
    public ParserService(ParserFactory parserFactory) {
        this.parserFactory = parserFactory;
    }

    public void parse(String... files) {
        Arrays.stream(files)
                .parallel()
                .forEach(file -> {
                    Path path = Paths.get(file);
                    Parser parser = parserFactory.getParser(file);
                    parser.parse(path);
                });
    }
}
