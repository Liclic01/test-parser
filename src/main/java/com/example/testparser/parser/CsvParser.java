package com.example.testparser.parser;

import com.example.testparser.model.OrderLine;
import com.example.testparser.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class CsvParser implements Parser {
    private final OrderValidator orderValidator;

    @Autowired
    public CsvParser(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    public void parse(Path path) {
        String fileName = path.toString();
        AtomicLong lineNumber = new AtomicLong(1);
        try {
            Files.lines(path)
                    .forEach(line -> {
                        System.out.println(parseLine(line, lineNumber.getAndIncrement(), fileName));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseLine(String line, long lineNumber, String fileName) {
        String[] fields = line.split(",");

        List<String> errorFields = orderValidator.validateOrderNumbers(fields[0], fields[1]);

        if (errorFields.size() == 0) {
            return new OrderLine(fields[0],
                    fields[1],
                    fields[2],
                    fields[3],
                    fileName,
                    lineNumber,
                    "OK").toString();
        }
        return new OrderLine(fields[0],
                fields[1],
                fields[2],
                fields[3],
                fileName,
                lineNumber,
                "Error with fields: " + errorFields.toString()).toString();
    }
}
