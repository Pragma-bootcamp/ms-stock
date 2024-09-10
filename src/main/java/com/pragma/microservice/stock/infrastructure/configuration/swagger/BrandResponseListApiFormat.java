package com.pragma.microservice.stock.infrastructure.configuration.swagger;

import com.pragma.microservice.stock.application.dto.response.brand.BrandResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;

import java.util.List;

public class BrandResponseListApiFormat extends ApiResponseFormat<List<BrandResponseDto>> {
    public BrandResponseListApiFormat(List<BrandResponseDto> data, MetadataResponse metadata) {
        super(data, metadata);
    }
}
