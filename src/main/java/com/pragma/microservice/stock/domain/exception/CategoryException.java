package com.pragma.microservice.stock.domain.exception;

import lombok.Getter;
import lombok.Setter;

public class CategoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final int errorCode;
    private final String errorMessage;

    public CategoryException(final int errorCode,final String errorMessage) {
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
