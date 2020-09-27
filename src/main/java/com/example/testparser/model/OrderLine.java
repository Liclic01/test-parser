package com.example.testparser.model;

public class OrderLine {
    private final String id;
    private final String amount;
    private final String currency;
    private final String comment;
    private final String fileName;
    private final long line;
    private final String result;

    public OrderLine(String id, String amount, String currency, String comment, String fileName, long line, String result) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.fileName = fileName;
        this.line = line;
        this.result = result;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", amount:" + amount +
                ", currency:'" + currency + '\'' +
                ", comment:'" + comment + '\'' +
                ", filename:'" + fileName + '\'' +
                ", line:'" + line + '\'' +
                ", result:'" + result + '\'' +
                '}';
    }
}
