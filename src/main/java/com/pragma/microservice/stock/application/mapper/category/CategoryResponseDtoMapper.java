package com.pragma.microservice.stock.application.mapper.category;

import com.pragma.microservice.stock.domain.model.Category;
import com.pragma.microservice.stock.application.dto.response.category.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryResponseDtoMapper {
    @Mapping(source = "id",target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description",target = "description")
    CategoryResponseDto toDto(Category category);
}
