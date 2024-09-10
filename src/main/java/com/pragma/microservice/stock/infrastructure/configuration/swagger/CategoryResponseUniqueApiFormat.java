package com.pragma.microservice.stock.infrastructure.configuration.swagger;

import com.pragma.microservice.stock.application.dto.response.category.CategoryResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;

public class CategoryResponseUniqueApiFormat extends ApiResponseFormat<CategoryResponseDto> {
    public CategoryResponseUniqueApiFormat(CategoryResponseDto data, MetadataResponse metadata) {
        super(data, metadata);
    }
}
