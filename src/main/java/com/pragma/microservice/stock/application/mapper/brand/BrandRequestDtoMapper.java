package com.pragma.microservice.stock.application.mapper.brand;

import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.application.dto.request.BrandRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandRequestDtoMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Brand toDomain(BrandRequestDto brand);
}
