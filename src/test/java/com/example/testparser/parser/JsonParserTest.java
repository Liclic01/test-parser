package com.example.testparser.parser;

import com.example.testparser.validator.OrderValidator;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class JsonParserTest {

    private static final Path PATH = Paths.get("src/test/resources/test.json");
    private OrderValidator orderValidator = mock(OrderValidator.class);
    private JsonParser jsonParser = new JsonParser(orderValidator);

    @Test
    void parse() {
        when(orderValidator.validateOrderNumbers("1", "fg")).thenReturn(Collections.singletonList("amount"));
        when(orderValidator.validateOrderNumbers("2", "100")).thenReturn(Collections.emptyList());
        when(orderValidator.validateOrderNumbers("3", "100")).thenReturn(Collections.emptyList());

        PrintStream outMock = mock(PrintStream.class);
        System.setOut(outMock);

        jsonParser.parse(PATH);
        verify(outMock).println("{id:1, amount:fg, currency:'USD', comment:'оплата заказа', filename:'src/test/resources/test.json', line:'1', result:'Error with fields: [amount]'}");
        verify(outMock).println("{id:2, amount:100, currency:'USD', comment:'оплата заказа', filename:'src/test/resources/test.json', line:'2', result:'OK'}");
        verify(outMock).println("{id:3, amount:100, currency:'USD', comment:'оплата заказа', filename:'src/test/resources/test.json', line:'3', result:'OK'}");
    }
}