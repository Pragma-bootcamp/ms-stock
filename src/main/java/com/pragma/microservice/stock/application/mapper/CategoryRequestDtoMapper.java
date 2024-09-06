package com.pragma.microservice.stock.application.mapper;

import com.pragma.microservice.stock.domain.model.Category;
import com.pragma.microservice.stock.application.dto.request.CategoryRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryRequestDtoMapper {
    @Mapping(source = "name", target = "name" )
    @Mapping(source = "description", target = "description")
    Category toDomain(CategoryRequestDto category);
}
