package com.pragma.microservice.stock.domain.port;

import com.pragma.microservice.stock.domain.model.Category;

import java.util.List;

public interface CategoryPersistencePort {
    Category createCategory(Category category);
}
