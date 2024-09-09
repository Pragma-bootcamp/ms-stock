package com.pragma.microservice.stock.infrastructure.utils;

public enum ArticleSortByEnum {
    NAME("name"),
    LAST_NAME("lastName"),
    SEAT_NUMBER("seatNumber");

    private final String field;
    ArticleSortByEnum(String field) {
        this.field = field;
    }
    public String getValue() {
        return field;
    }
}

