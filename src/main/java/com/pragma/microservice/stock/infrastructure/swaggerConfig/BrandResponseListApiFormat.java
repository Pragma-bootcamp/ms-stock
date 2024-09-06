package com.pragma.microservice.stock.infrastructure.swaggerConfig;

import com.pragma.microservice.stock.application.dto.response.BrandResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;

import java.util.List;

public class BrandResponseListApiFormat extends ApiResponseFormat<List<BrandResponseDto>> {
    public BrandResponseListApiFormat(List<BrandResponseDto> data, MetadataResponse metadata) {
        super(data, metadata);
    }
}
