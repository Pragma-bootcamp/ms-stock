package com.pragma.microservice.stock.application.service;

import com.pragma.microservice.stock.application.mapper.CategoryRequestDtoMapper;
import com.pragma.microservice.stock.application.mapper.CategoryResponseDtoMapper;
import com.pragma.microservice.stock.domain.model.Category;
import com.pragma.microservice.stock.application.dto.request.CategoryRequestDto;
import com.pragma.microservice.stock.application.dto.response.CategoryResponseDto;
import com.pragma.microservice.stock.domain.port.CategoryPersistencePort;
import com.pragma.microservice.stock.application.usecase.CategoryUseCase;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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
    public ApiResponseFormat<CategoryResponseDto> createCategory(CategoryRequestDto categoryRequest) {
        Category categoryToCreate = categoryRequestDtoMapper.toDomain(categoryRequest);
        categoryToCreate.setCreatedAt(LocalDateTime.now());
        categoryToCreate.setUpdatedAt(LocalDateTime.now());
        Category categoryCreated = categoryPersistencePort.createCategory(categoryToCreate);
        return new ApiResponseFormat<>(categoryResponseDtoMapper.toDto(categoryCreated),null);
    }
    @Override
    public ApiResponseFormat<List<CategoryResponseDto>> getAllCategories(int page, int size, Sort.Direction direction) {
        ApiResponseFormat<List<Category>> response = categoryPersistencePort.getAllCategories(page, size);
        Comparator<Category> comparator = Comparator.comparing(Category::getName);
        if (direction == Sort.Direction.DESC) {
            comparator = comparator.reversed();
        }
        List<CategoryResponseDto> sortedCategories = response.getData().stream().sorted(comparator).map(categoryResponseDtoMapper::toDto)
                .toList();
        return new ApiResponseFormat<>(sortedCategories,response.getMetadata());
    }
}
