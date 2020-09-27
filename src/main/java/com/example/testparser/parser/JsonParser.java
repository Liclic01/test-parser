package com.example.testparser.parser;

import com.example.testparser.model.OrderLine;
import com.example.testparser.validator.OrderValidator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
public class JsonParser implements Parser {
    private final OrderValidator orderValidator;

    @Autowired
    public JsonParser(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    public void parse(Path path) {
        String fileName = path.toString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(path.toFile());
            for (int i = 0; i < rootNode.size(); i++) {
                System.out.println(parseNode(rootNode.get(i), i + 1, fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseNode(JsonNode node, int lineNumber, String fileName) {
        String orderId = node.get("orderId").asText();
        String amount = node.get("amount").asText();

        List<String> errorFields = orderValidator.validateOrderNumbers(orderId, amount);

        if (errorFields.size() == 0) {
            return new OrderLine(node.get("orderId").asText(),
                    node.get("amount").asText(),
                    node.get("currency").textValue(),
                    node.get("comment").textValue(),
                    fileName,
                    lineNumber,
                    "OK").toString();
        }
        return new OrderLine(node.get("orderId").asText(),
                node.get("amount").asText(),
                node.get("currency").textValue(),
                node.get("comment").textValue(),
                fileName,
                lineNumber,
                "Error with fields: " + errorFields.toString()).toString();
    }
}
