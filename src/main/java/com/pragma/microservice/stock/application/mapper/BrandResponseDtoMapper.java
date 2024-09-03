package com.pragma.microservice.stock.application.mapper;

import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.domain.model.Category;
import com.pragma.microservice.stock.domain.model.dto.request.BrandRequestDto;
import com.pragma.microservice.stock.domain.model.dto.request.CategoryRequestDto;
import com.pragma.microservice.stock.domain.model.dto.response.BrandResponseDto;
import com.pragma.microservice.stock.domain.model.dto.response.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface BrandResponseDtoMapper {
    @Mapping(source = "id",target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description",target = "description")
    BrandResponseDto toDto(Brand category);
}
