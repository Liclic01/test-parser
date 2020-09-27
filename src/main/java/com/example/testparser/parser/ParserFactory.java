package com.example.testparser.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParserFactory {
    @Autowired
    private CsvParser csvParser;

    @Autowired
    private JsonParser jsonParser;

    public Parser getParser(String fileName) {
        String fileExtension = getFileExtension(fileName);
        switch (fileExtension.toLowerCase()) {
            case "csv":
                return csvParser;
            case "json":
                return jsonParser;
            default:
                throw new IllegalArgumentException("Extension not supported");
        }
    }

    private String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return fileName.substring(lastIndexOf + 1);
    }
}
