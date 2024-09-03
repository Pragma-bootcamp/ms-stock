package com.pragma.microservice.stock.infrastructure.swaggerConfig;

import com.pragma.microservice.stock.domain.model.dto.response.BrandResponseDto;
import com.pragma.microservice.stock.domain.model.dto.response.CategoryResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;

import java.util.List;

public class BrandResponseApiFormat extends ApiResponseFormat<List<BrandResponseDto>> {
    public BrandResponseApiFormat(List<BrandResponseDto> data, MetadataResponse metadata) {
        super(data, metadata);
    }
}
