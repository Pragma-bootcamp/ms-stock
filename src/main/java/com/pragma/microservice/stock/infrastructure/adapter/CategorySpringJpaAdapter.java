package com.pragma.microservice.stock.infrastructure.adapter;

import com.pragma.microservice.stock.domain.exception.CategoryException;
import com.pragma.microservice.stock.domain.model.Category;
import com.pragma.microservice.stock.domain.model.constant.CategoryConstant;
import com.pragma.microservice.stock.domain.port.CategoryPersistencePort;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;
import com.pragma.microservice.stock.infrastructure.adapter.entity.CategoryEntity;
import com.pragma.microservice.stock.infrastructure.adapter.mapper.CategoryDboMapper;
import com.pragma.microservice.stock.infrastructure.adapter.repository.CategoryRepository;
import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CategorySpringJpaAdapter implements CategoryPersistencePort {
    private final CategoryRepository categoryRepository;
    private final CategoryDboMapper categoryDboMapper;


    public CategorySpringJpaAdapter(CategoryRepository categoryRepository, CategoryDboMapper categoryDboMapper ) {
        this.categoryRepository = categoryRepository;
        this.categoryDboMapper = categoryDboMapper;
    }

    @Override
    public Category createCategory(Category category) {
        List<CategoryEntity> existingCategory = categoryRepository.findByName(category.getName());
        if (!existingCategory.isEmpty()) {
            throw new CategoryException(HttpStatus.CONFLICT.value(), String.format(CategoryConstant.CATEGORY_ALREADY_EXISTS_MESSAGE_ERROR, category.getName()));
        }
        CategoryEntity categoryToSave = categoryDboMapper.toDbo(category);
        CategoryEntity savedCategory = categoryRepository.save(categoryToSave);
        return categoryDboMapper.toDomain(savedCategory);
    }

    @Override
    public ApiResponseFormat<List<Category>> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);
        List<Category> categories = categoryPage.getContent()
                .stream()
                .map(categoryDboMapper::toDomain).toList();
        MetadataResponse meta = new MetadataResponse(page, categoryPage.getTotalElements(), categoryPage.getTotalPages(), size);
        return new ApiResponseFormat<>(categories, meta);
    }

    @Override
    public Category getCategory(Long categoryId) {
        Optional<CategoryEntity> existingCategory = categoryRepository.findById(categoryId);
        if (existingCategory.isEmpty()) {
            throw new CategoryException(HttpStatus.NOT_FOUND.value(), String.format(CategoryConstant.CATEGORY_NOT_FOUND, categoryId));
        }
        return categoryDboMapper.toDomain(existingCategory.get());
    }
}
