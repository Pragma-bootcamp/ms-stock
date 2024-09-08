package com.pragma.microservice.stock.application.mapper;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.domain.model.Article;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleRequestDtoMapper {
    @Mapping(source = "name", target = "name" )
    @Mapping(source = "description", target = "description")
    Article toDomain(ArticleRequestDto article);
}
