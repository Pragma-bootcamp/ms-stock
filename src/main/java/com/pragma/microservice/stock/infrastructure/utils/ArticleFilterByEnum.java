package com.pragma.microservice.stock.infrastructure.utils;

public enum ArticleFilterByEnum {
    CATEGORY("category"),
    BRAND("brand"),
    NONE("none");

    private final String field;
    ArticleFilterByEnum(String field) {
        this.field = field;
    }
    public String getValue() {
        return field;
    }
}
