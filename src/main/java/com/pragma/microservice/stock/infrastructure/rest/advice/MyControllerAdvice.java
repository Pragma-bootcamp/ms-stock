package com.pragma.microservice.stock.infrastructure.rest.advice;

import com.pragma.microservice.stock.domain.exception.CategoryException;
import com.pragma.microservice.stock.domain.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
@ControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ErrorResponse> handleCategoryException(CategoryException categoryException) {
        ErrorResponse errorResponse = new ErrorResponse(categoryException.getErrorMessage(), categoryException.getErrorCode());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
