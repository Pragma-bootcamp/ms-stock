package com.pragma.microservice.stock.application.service;

import com.pragma.microservice.stock.application.mapper.brand.BrandRequestDtoMapper;
import com.pragma.microservice.stock.application.mapper.brand.BrandResponseDtoMapper;
import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.application.dto.request.BrandRequestDto;
import com.pragma.microservice.stock.application.dto.response.brand.BrandResponseDto;
import com.pragma.microservice.stock.domain.port.BrandPersistencePort;
import com.pragma.microservice.stock.application.usecase.BrandUseCase;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class BrandService implements BrandUseCase {
    private final BrandPersistencePort brandPersistencePort;
    private final BrandRequestDtoMapper brandRequestDtoMapper;
    private final BrandResponseDtoMapper brandResponseDtoMapper;

    public BrandService(final BrandPersistencePort brandPersistencePort,final BrandRequestDtoMapper brandRequestDtoMapper,final BrandResponseDtoMapper brandResponseDtoMapper) {
        this.brandPersistencePort = brandPersistencePort;
        this.brandRequestDtoMapper = brandRequestDtoMapper;
        this.brandResponseDtoMapper = brandResponseDtoMapper;
    }

    @Override
    public ApiResponseFormat<BrandResponseDto> createBrand(BrandRequestDto brandRequestDto) {
        Brand brandToCreate = brandRequestDtoMapper.toDomain(brandRequestDto);
        brandToCreate.setCreatedAt(LocalDateTime.now());
        brandToCreate.setUpdatedAt(LocalDateTime.now());
        Brand brandCreate = brandPersistencePort.createBrand(brandToCreate);
        return new ApiResponseFormat<>(brandResponseDtoMapper.toDto(brandCreate),null);
    }

    @Override
    public ApiResponseFormat<List<BrandResponseDto>> getAllBrand(int page, int size, Sort.Direction direction) {
        ApiResponseFormat<List<Brand>> response = brandPersistencePort.getAllBrands(page, size);
        Comparator<Brand> comparator = Comparator.comparing(Brand::getName);
        if (direction == Sort.Direction.DESC) {
            comparator = comparator.reversed();
        }
        List<BrandResponseDto> sortedBrands = response.getData().stream().sorted(comparator).map(brandResponseDtoMapper::toDto)
                .toList();
        return new ApiResponseFormat<>(sortedBrands,response.getMetadata());
    }
}
