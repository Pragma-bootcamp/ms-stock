package com.pragma.microservice.stock.domain.model.constant;

public class ArticleConstant {
    private ArticleConstant() {}
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_DESCRIPTION_LENGTH = 120;
    public static final int MIN_DESCRIPTION_LENGTH = 3;
    public static final String ARTICLE_ALREADY_EXISTS_MESSAGE_ERROR = "The article %s already exist";
    public static final String ARTICLE_NAME_CANNOT_BE_NULL = "The field  'name' can't be null";
    public static final String ARTICLE_DESCRIPTION_CANNOT_BE_NULL = "The field  'description' can't be null";
    public static final String ARTICLE_NAME_MUST_HAVE_LENGTH = "The field 'name' must have mix 3 characters and max 50";
    public static final String ARTICLE_DESCRIPTION_MUST_HAVE_LENGTH = "The field  'description' must have mix 3 characters and max 90";
    public static final String CATEGORIES_CANT_BE_DUPLICATED = "The categories of the article %s can't be duplicates";
    public static final String CANNOT_ADD_MORE_CATEGORIES= "The article cannot have more than 3 categories";
    public static final String CATEGORIES_MUST_HAVE_LEAST_ONE= "The article must have at least one category";
    public static final String ARTICLE_AMOUNT_CANNOT_BE_NULL = "The field 'amount' can't be null";
    public static final String ARTICLE_PRICE_CANNOT_BE_NULL = "The field 'price' can't be null";
    public static final String ARTICLE_CATEGORIES_CANNOT_BE_NULL = "The field 'categories' can't be null";
}
