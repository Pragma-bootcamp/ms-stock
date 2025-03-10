package com.pragma.microservice.stock.infrastructure.utils;

public enum ArticleSortByEnum {
    NAME("name"),
    PRICE("price"),
    AMOUNT("amount");

    private final String field;
    ArticleSortByEnum(String field) {
        this.field = field;
    }
    public String getValue() {
        return field;
    }
}

