package com.pragma.microservice.stock.domain.model.constant;

public class BrandConstant {
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_DESCRIPTION_LENGTH = 120;
    public static final int MIN_DESCRIPTION_LENGTH = 3;
    public static final String BRAND_ALREADY_EXISTS_MESSAGE_ERROR = "The brand %s already exist";
    public static final String BRAND_NAME_CANNOT_BE_NULL = "The field  \"name\" can't be null";
    public static final String BRAND_DESCRIPTION_CANNOT_BE_NULL = "The field  \"description\" can't be null";
    public static final String BRAND_NAME_MUST_HAVE_LENGTH = "The field \"name\" must have mix 3 characters and max 50";
    public static final String BRAND_DESCRIPTION_MUST_HAVE_LENGTH = "The field  \"description\" must have mix 3 characters and max 90";
}
