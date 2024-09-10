package com.pragma.microservice.stock.infrastructure.rest.advice;

import com.pragma.microservice.stock.application.exception.FilterException;
import com.pragma.microservice.stock.application.exception.PaginationException;
import com.pragma.microservice.stock.domain.exception.ArticleException;
import com.pragma.microservice.stock.domain.exception.BrandException;
import com.pragma.microservice.stock.domain.exception.CategoryException;
import com.pragma.microservice.stock.domain.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
    @ExceptionHandler(BrandException.class)
    public ResponseEntity<ErrorResponse> handleBrandException(BrandException brandException) {
        ErrorResponse errorResponse = new ErrorResponse(brandException.getErrorMessage(), brandException.getErrorCode());
        return ResponseEntity.status(brandException.getErrorCode()).body(errorResponse);
    }
    @ExceptionHandler(ArticleException.class)
    public ResponseEntity<ErrorResponse> handleArticleException(ArticleException articleException) {
        ErrorResponse errorResponse = new ErrorResponse(articleException.getErrorMessage(), articleException.getErrorCode());
        return ResponseEntity.status(articleException.getErrorCode()).body(errorResponse);
    }
    @ExceptionHandler(FilterException.class)
    public ResponseEntity<ErrorResponse> handleAFilterException(FilterException filterException) {
        ErrorResponse errorResponse = new ErrorResponse(filterException.getErrorMessage(), filterException.getErrorCode());
        return ResponseEntity.status(filterException.getErrorCode()).body(errorResponse);
    }
    @ExceptionHandler(PaginationException.class)
    public ResponseEntity<ErrorResponse> handleArticleException(PaginationException paginationException) {
        ErrorResponse errorResponse = new ErrorResponse(paginationException.getErrorMessage(), paginationException.getErrorCode());
        return ResponseEntity.status(paginationException.getErrorCode()).body(errorResponse);
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
        String errorMessage = String.format("El parámetro '%s' tiene un valor inválido: '%s'", ex.getName(), ex.getMostSpecificCause().getMessage());
        ErrorResponse response = new ErrorResponse(errorMessage,HttpStatus.BAD_REQUEST.value() );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MissingServletRequestParameterException ex) {
        ErrorResponse errResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errResponse);
    }

}
