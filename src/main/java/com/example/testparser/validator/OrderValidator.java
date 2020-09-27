package com.example.testparser.validator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class OrderValidator {
    public List<String> validateOrderNumbers(String... fields) {
        List<String> errorFields = new ArrayList();
        Arrays.stream(fields).forEach(field -> {
            try {
                Long.parseLong(field);
            } catch (NumberFormatException e) {
                errorFields.add(field);
            }
        });
        return errorFields;
    }
}
