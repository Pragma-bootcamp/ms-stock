package com.pragma.microservice.stock.application.mapper;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.application.dto.response.ArticleResponseDto;
import com.pragma.microservice.stock.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleResponseDtoMapper {
    @Mapping(source = "id", target = "id" )
    @Mapping(source = "name", target = "name" )
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categories", target = "categories")
    Article toDto(ArticleResponseDto article);
}
