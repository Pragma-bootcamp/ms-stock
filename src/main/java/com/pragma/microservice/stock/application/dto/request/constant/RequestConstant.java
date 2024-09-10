package com.pragma.microservice.stock.application.dto.request.constant;

public class RequestConstant {
    private RequestConstant() {}
    public static final String ORDER_BY_FIELDS = "The param 'orderDir' must be 'asc' or 'desc'";
    public static final String FILTER_BY_FIELDS = "The param 'filterBy' must be 'category' or 'brand'";
    public static final String FILTER_VALUE_CANT_BE_NULL = "The param '%s' mustn't be 'null'";
    public static final String FILTER_VALUE_MUST_BE_NUMBER = "The param '%s' must be a number";
    public static final String PAGINATION_CANNOT_BE_NEGATIVE = "The params 'size' or 'page' mustn't be negative";
}
