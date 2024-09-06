package com.pragma.microservice.stock.application.mapper;

import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.application.dto.request.BrandRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface BrandRequestDtoMapper {
    @Mapping(source = "name", target = "name" )
    @Mapping(source = "description", target = "description")
    Brand toDomain(BrandRequestDto category);
}
