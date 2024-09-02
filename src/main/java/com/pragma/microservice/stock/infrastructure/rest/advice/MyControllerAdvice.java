package com.pragma.microservice.stock.infrastructure.rest.advice;

import com.pragma.microservice.stock.domain.exception.CategoryException;
import com.pragma.microservice.stock.domain.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class MyControllerAdvice {
    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<ErrorResponse> handleCategoryException(CategoryException categoryException) {
        ErrorResponse errorResponse = new ErrorResponse(categoryException.getErrorMessage(), categoryException.getErrorCode());
        return ResponseEntity.status(categoryException.getErrorCode()).body(errorResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.append(errorMessage).append("\n");
        });
        ErrorResponse errResponse = new ErrorResponse(errors.toString().trim(),HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResponse);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorMessage = String.format("El parámetro '%s' tiene un valor inválido: '%s'. Debe ser un número.", ex.getName(), ex.getValue());
        ErrorResponse response = new ErrorResponse(errorMessage,HttpStatus.BAD_REQUEST.value() );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
