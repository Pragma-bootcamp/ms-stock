package com.pragma.microservice.stock.application.exception;

public class FilterException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final int errorCode;
    private final String errorMessage;
    public FilterException(final int errorCode,final String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
