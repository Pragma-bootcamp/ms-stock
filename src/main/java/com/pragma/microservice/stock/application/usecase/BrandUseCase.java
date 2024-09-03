package com.pragma.microservice.stock.application.usecase;

import com.pragma.microservice.stock.domain.model.dto.request.BrandRequestDto;
import com.pragma.microservice.stock.domain.model.dto.response.BrandResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BrandUseCase {
    ApiResponseFormat<BrandResponseDto> createBrand(BrandRequestDto categoryRequest);
    ApiResponseFormat<List<BrandResponseDto>> getAllBrand(int page, int size, Sort.Direction direction);
}
