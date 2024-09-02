package com.pragma.microservice.stock.application.usecase;

import com.pragma.microservice.stock.domain.model.dto.request.CategoryRequestDto;
import com.pragma.microservice.stock.domain.model.dto.response.CategoryResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryUseCase {
    ApiResponseFormat<CategoryResponseDto> createCategory(CategoryRequestDto categoryRequest);
    ApiResponseFormat<List<CategoryResponseDto>> getAllCategories(int page, int size, Sort.Direction direction);
}
