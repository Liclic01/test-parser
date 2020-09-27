package com.example.testparser.validator;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorTest {
    private OrderValidator orderValidator = new OrderValidator();

    @Test
    void validateOrderNumbers_isNumber_returnEmptyList() {
       List<String> errors = orderValidator.validateOrderNumbers("111");
        assertEquals(0, errors.size());
    }

    @Test
    void validateOrderNumbers_isNotNumber_returnListWithField() {
        List<String> errors = orderValidator.validateOrderNumbers("fg");
        assertEquals(1, errors.size());
    }
}