package com.pragma.microservice.stock.application.mapper.brand;

import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.application.dto.response.brand.BrandResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface BrandResponseDtoMapper {
    @Mapping(source = "id",target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description",target = "description")
    BrandResponseDto toDto(Brand category);
}
