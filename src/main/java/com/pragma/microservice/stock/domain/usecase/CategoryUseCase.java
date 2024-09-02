package com.pragma.microservice.stock.domain.usecase;

import com.pragma.microservice.stock.domain.model.dto.request.CategoryRequestDto;
import com.pragma.microservice.stock.domain.model.dto.response.CategoryResponseDto;

public interface CategoryUseCase {
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequest);
}
