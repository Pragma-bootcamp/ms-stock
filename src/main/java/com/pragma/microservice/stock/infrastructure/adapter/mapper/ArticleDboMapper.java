package com.pragma.microservice.stock.infrastructure.adapter.mapper;

import com.pragma.microservice.stock.domain.model.Article;
import com.pragma.microservice.stock.infrastructure.adapter.entity.ArticleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface ArticleDboMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categories",target = "categories")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    ArticleEntity toDbo(Article article);

    @InheritInverseConfiguration
    Article toDomain (ArticleEntity entity);
}
