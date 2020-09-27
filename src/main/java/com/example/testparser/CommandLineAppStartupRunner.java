package com.example.testparser;

import com.example.testparser.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final ParserService parserService;

    @Autowired
    public CommandLineAppStartupRunner(ParserService parserService) {
        this.parserService = parserService;
    }

    @Override
    public void run(String... args) throws Exception {
        parserService.parse(args);
    }
}