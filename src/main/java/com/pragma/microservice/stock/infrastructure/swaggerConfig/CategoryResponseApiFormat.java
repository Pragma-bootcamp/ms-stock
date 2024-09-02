package com.pragma.microservice.stock.infrastructure.swaggerConfig;

import com.pragma.microservice.stock.domain.model.dto.response.CategoryResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;

public class CategoryResponseApiFormat extends ApiResponseFormat<CategoryResponseDto> {
    public CategoryResponseApiFormat(CategoryResponseDto data, MetadataResponse metadata) {
        super(data, metadata);
    }
    // No es necesario agregar código aquí, solo usar la clase específica.
}