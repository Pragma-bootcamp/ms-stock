package com.pragma.microservice.stock.domain.model.constant;

public class CategoryConstant {
    private CategoryConstant() {}
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_DESCRIPTION_LENGTH = 90;
    public static final int MIN_DESCRIPTION_LENGTH = 3;
    public static final String CATEGORY_ALREADY_EXISTS_MESSAGE_ERROR = "The category %s already exist";
    public static final String CATEGORY_NAME_CANNOT_BE_NULL = "The field  \"name\" can't be null";
    public static final String CATEGORY_DESCRIPTION_CANNOT_BE_NULL = "The field  \"description\" can't be null";
    public static final String CATEGORY_NAME_MUST_HAVE_LENGTH = "The field \"name\" must have mix 3 characters and max 50";
    public static final String CATEGORY_DESCRIPTION_MUST_HAVE_LENGTH = "The field  \"description\" must have mix 3 characters and max 90";
    public static final String CATEGORY_NOT_FOUND = "The category %d not found";
}
