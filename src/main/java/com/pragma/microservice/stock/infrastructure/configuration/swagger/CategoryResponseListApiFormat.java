package com.pragma.microservice.stock.infrastructure.configuration.swagger;

import com.pragma.microservice.stock.application.dto.response.category.CategoryResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;

import java.util.List;

public class CategoryResponseListApiFormat extends ApiResponseFormat<List<CategoryResponseDto>> {
    public CategoryResponseListApiFormat(List<CategoryResponseDto> data, MetadataResponse metadata) {
        super(data, metadata);
    }
}