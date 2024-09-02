package com.pragma.microservice.stock.application.service;

import com.pragma.microservice.stock.application.mapper.CategoryRequestDtoMapper;
import com.pragma.microservice.stock.application.mapper.CategoryResponseDtoMapper;
import com.pragma.microservice.stock.domain.model.Category;
import com.pragma.microservice.stock.domain.model.dto.request.CategoryRequestDto;
import com.pragma.microservice.stock.domain.model.dto.response.CategoryResponseDto;
import com.pragma.microservice.stock.domain.port.CategoryPersistencePort;
import com.pragma.microservice.stock.domain.usecase.CategoryUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryService implements CategoryUseCase {
    private final CategoryPersistencePort categoryPersistencePort;
    private final CategoryRequestDtoMapper categoryRequestDtoMapper;
    private final CategoryResponseDtoMapper categoryResponseDtoMapper;

    public CategoryService(final CategoryPersistencePort categoryPersistencePort, final CategoryRequestDtoMapper categoryRequestDtoMapper, final CategoryResponseDtoMapper categoryResponseDtoMapper) {
        this.categoryPersistencePort = categoryPersistencePort;
        this.categoryRequestDtoMapper = categoryRequestDtoMapper;
        this.categoryResponseDtoMapper = categoryResponseDtoMapper;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequest) {
        Category categoryToCreate = categoryRequestDtoMapper.toDomain(categoryRequest);
        categoryToCreate.setCreatedAt(LocalDateTime.now());
        categoryToCreate.setUpdatedAt(LocalDateTime.now());
        Category categoryCreated = categoryPersistencePort.createCategory(categoryToCreate);
        return categoryResponseDtoMapper.toDto(categoryCreated);
    }
}
