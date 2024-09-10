package com.pragma.microservice.stock.infrastructure.configuration.swagger;

import com.pragma.microservice.stock.application.dto.response.brand.BrandResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;

public class BrandResponseUniqueApiFormat extends ApiResponseFormat<BrandResponseDto> {
    public BrandResponseUniqueApiFormat(BrandResponseDto data, MetadataResponse metadata) {
        super(data, metadata);
    }
}
