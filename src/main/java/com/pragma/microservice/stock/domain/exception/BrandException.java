package com.pragma.microservice.stock.domain.exception;

public class BrandException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private final int errorCode;
  private final String errorMessage;
  public BrandException(final int errorCode,final String errorMessage) {
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
