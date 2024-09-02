package com.pragma.microservice.stock.domain.model.constant;

public class BrandConstant {
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_DESCRIPTION_LENGTH = 90;
    public static final int MIN_DESCRIPTION_LENGTH = 3;
    public static final String BRAND_ALREADY_EXISTS_MESSAGE_ERROR = "La marca %s ya existe";
    public static final String BRAND_NAME_CANNOT_BE_NULL = "El campo \"name\" no puede ser nulo";
    public static final String BRAND_DESCRIPTION_CANNOT_BE_NULL = "El campo \"description\" no puede ser nulo";
    public static final String BRAND_NAME_MUST_HAVE_LENGTH = "El campo \"name\" dbe tener minimo 3 caracteres y maximo 50 ";
    public static final String BRAND_DESCRIPTION_MUST_HAVE_LENGTH = "El campo \"description\" dbe tener minimo 3 caracteres y  maximo 90";
}
