package com.pragma.microservice.stock.domain.port;

import com.pragma.microservice.stock.domain.model.Category;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;

import java.util.List;

public interface CategoryPersistencePort {
    Category createCategory(Category category);
    ApiResponseFormat<List<Category>> getAllCategories(int page, int size);
    Category getCategory(Long categoryId);
}
