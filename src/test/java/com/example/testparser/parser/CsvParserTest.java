package com.example.testparser.parser;

import com.example.testparser.validator.OrderValidator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.mockito.Mockito.*;

class CsvParserTest {
    private static final Path PATH = Paths.get("src/test/resources/test.csv");
    private OrderValidator orderValidator = mock(OrderValidator.class);
    private CsvParser csvParser = new CsvParser(orderValidator);

    @Test
    void parse() {
        when(orderValidator.validateOrderNumbers("1", "fg")).thenReturn(Collections.singletonList("amount"));
        when(orderValidator.validateOrderNumbers("2", "100")).thenReturn(Collections.emptyList());
        when(orderValidator.validateOrderNumbers("3", "100")).thenReturn(Collections.emptyList());

        PrintStream outMock = mock(PrintStream.class);
        System.setOut(outMock);

        csvParser.parse(PATH);
        verify(outMock).println("{id:1, amount:fg, currency:'USD', comment:'оплата заказа', filename:'src/test/resources/test.csv', line:'1', result:'Error with fields: [amount]'}");
        verify(outMock).println("{id:2, amount:100, currency:'USD', comment:'оплата заказа', filename:'src/test/resources/test.csv', line:'2', result:'OK'}");
        verify(outMock).println("{id:3, amount:100, currency:'USD', comment:'оплата заказа', filename:'src/test/resources/test.csv', line:'3', result:'OK'}");
    }
}