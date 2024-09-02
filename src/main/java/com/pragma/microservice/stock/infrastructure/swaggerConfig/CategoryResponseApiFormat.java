package com.pragma.microservice.stock.infrastructure.swaggerConfig;

import com.pragma.microservice.stock.domain.model.dto.response.CategoryResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;

import java.util.List;

public class CategoryResponseApiFormat extends ApiResponseFormat<List<CategoryResponseDto>> {
    public CategoryResponseApiFormat(List<CategoryResponseDto> data, MetadataResponse metadata) {
        super(data, metadata);
    }
}