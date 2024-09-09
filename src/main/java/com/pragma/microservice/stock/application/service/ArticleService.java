package com.pragma.microservice.stock.application.service;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.application.dto.response.ArticleResponseDto;
import com.pragma.microservice.stock.application.mapper.ArticleRequestDtoMapper;
import com.pragma.microservice.stock.application.mapper.ArticleResponseDtoMapper;
import com.pragma.microservice.stock.application.mapper.CategoryResponseDtoMapper;
import com.pragma.microservice.stock.application.usecase.ArticleUseCase;
import com.pragma.microservice.stock.domain.exception.ArticleException;
import com.pragma.microservice.stock.domain.model.Article;
import com.pragma.microservice.stock.domain.model.Category;
import com.pragma.microservice.stock.domain.model.constant.ArticleConstant;
import com.pragma.microservice.stock.domain.port.ArticlePersistencePort;
import com.pragma.microservice.stock.domain.port.CategoryPersistencePort;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ArticleService implements ArticleUseCase {
    private final ArticleResponseDtoMapper articleResponseDtoMapper;
    private final ArticleRequestDtoMapper articleRequestDtoMapper;
    private final ArticlePersistencePort articlePersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final CategoryResponseDtoMapper categoryResponseDtoMapper;

    public ArticleService(final ArticleResponseDtoMapper articleResponseDtoMapper,
                          final ArticleRequestDtoMapper articleRequestDtoMapper,
                          final ArticlePersistencePort articlePersistencePort,
                          final CategoryPersistencePort categoryPersistencePort,
                          final CategoryResponseDtoMapper categoryResponseDtoMapper) {
        this.articleResponseDtoMapper = articleResponseDtoMapper;
        this.articleRequestDtoMapper = articleRequestDtoMapper;
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.categoryResponseDtoMapper = categoryResponseDtoMapper;
    }
    @Override
    public ApiResponseFormat<ArticleResponseDto> createArticle(ArticleRequestDto articleRequestDto) {
        if(articleRequestDto.getCategories().isEmpty()) {
            throw new ArticleException(HttpStatus.BAD_REQUEST.value(), ArticleConstant.CATEGORIES_MUST_HAVE_LEAST_ONE);
        }
        if (new HashSet<Long>(articleRequestDto.getCategories()).size() != articleRequestDto.getCategories().size()) {
            throw new ArticleException(HttpStatus.BAD_REQUEST.value(), ArticleConstant.CATEGORIES_CANT_BE_DUPLICATED);
        }

        Article articleToCreate = articleRequestDtoMapper.toDomain(articleRequestDto);
        List<Category> categories = new ArrayList<>();
        for (Long categoryId : articleRequestDto.getCategories()) {
           Category articleCategory = categoryPersistencePort.getCategory(categoryId);
           categories.add(articleCategory);
        }
        articleToCreate.setCategories(categories);
        Article articleCreated = articlePersistencePort.createArticle(articleToCreate);
        return new ApiResponseFormat<>(articleResponseDtoMapper.toDto(articleCreated),null);
    }
}
