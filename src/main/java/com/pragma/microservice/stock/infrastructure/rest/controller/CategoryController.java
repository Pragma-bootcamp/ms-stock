package com.pragma.microservice.stock.infrastructure.rest.controller;

import com.pragma.microservice.stock.application.service.CategoryService;
import com.pragma.microservice.stock.domain.model.dto.request.CategoryRequestDto;
import com.pragma.microservice.stock.domain.model.dto.response.CategoryResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")

public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping()
    public ApiResponse<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequest) {
        CategoryResponseDto response = categoryService.createCategory(categoryRequest);
        return new ApiResponse<>(response);
    }
}
