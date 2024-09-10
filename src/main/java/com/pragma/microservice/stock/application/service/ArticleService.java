package com.pragma.microservice.stock.application.service;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.application.dto.request.constant.RequestConstant;
import com.pragma.microservice.stock.application.dto.response.article.ArticleResponseDto;
import com.pragma.microservice.stock.application.exception.FilterException;
import com.pragma.microservice.stock.application.exception.PaginationException;
import com.pragma.microservice.stock.application.mapper.article.ArticleRequestDtoMapper;
import com.pragma.microservice.stock.application.mapper.article.ArticleResponseDtoMapper;
import com.pragma.microservice.stock.application.usecase.ArticleUseCase;
import com.pragma.microservice.stock.domain.exception.ArticleException;
import com.pragma.microservice.stock.domain.model.Article;

import com.pragma.microservice.stock.domain.model.constant.ArticleConstant;
import com.pragma.microservice.stock.domain.port.ArticlePersistencePort;
import com.pragma.microservice.stock.domain.port.BrandPersistencePort;
import com.pragma.microservice.stock.domain.port.CategoryPersistencePort;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService implements ArticleUseCase {
    private final ArticleResponseDtoMapper articleResponseDtoMapper;
    private final ArticleRequestDtoMapper articleRequestDtoMapper;
    private final ArticlePersistencePort articlePersistencePort;
    private final CategoryPersistencePort categoryPersistencePort;
    private final BrandPersistencePort brandPersistencePort;

    public ArticleService(final ArticleResponseDtoMapper articleResponseDtoMapper,
                          final ArticleRequestDtoMapper articleRequestDtoMapper,
                          final ArticlePersistencePort articlePersistencePort,
                          final CategoryPersistencePort categoryPersistencePort,
                          final BrandPersistencePort brandPersistencePort) {
        this.articleResponseDtoMapper = articleResponseDtoMapper;
        this.articleRequestDtoMapper = articleRequestDtoMapper;
        this.articlePersistencePort = articlePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public ApiResponseFormat<ArticleResponseDto> createArticle(ArticleRequestDto articleRequestDto) {
        articleRequestDto.validate();
        Article articleToCreate = articleRequestDtoMapper.toDomain(articleRequestDto);
        articleToCreate.setBrand(brandPersistencePort.getBrand(articleRequestDto.getBrand()));
        articleToCreate.setCategories(articleRequestDto.getCategories()
                .stream()
                .map(categoryPersistencePort::getCategory)
                .collect(Collectors.toSet()));
        Article articleCreated = articlePersistencePort.createArticle(articleToCreate);
        return new ApiResponseFormat<>(articleResponseDtoMapper.toDto(articleCreated), null);
    }

    @Override
    public ApiResponseFormat<List<ArticleResponseDto>> getAllArticles(Integer pageNumber, Integer pageSize,
                                                                      String sortBy, String sortDirection,
                                                                      String filterBy, String filterValue) {
        //Validate pagination and filters
        validatePagination(pageNumber, pageSize, sortBy, sortDirection, filterBy, filterValue);
        //Get the Articles
        ApiResponseFormat<List<Article>> allArticles =
                articlePersistencePort.getAllArticles(pageNumber, pageSize, sortBy,
                        sortDirection, filterBy, filterValue);
        //Transform to response
        List<ArticleResponseDto> listArticlesResponse = allArticles
                .getData()
                .stream()
                .map(articleResponseDtoMapper::toDto).toList();
        return new ApiResponseFormat<>(listArticlesResponse, allArticles.getMetadata());
    }

    public void validatePagination(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection,
                                   String filterBy, String filterValue) {
        boolean isCategoryFilter = filterBy.equals("category");
        if (pageNumber < 0 || pageSize < 0) {
            throw new PaginationException(HttpStatus.BAD_REQUEST.value(), RequestConstant.PAGINATION_CANNOT_BE_NEGATIVE);
        }
        if (!(sortBy.equals("name") || sortBy.equals("price") || sortBy.equals("amount"))) {
            throw new FilterException(HttpStatus.BAD_REQUEST.value(), ArticleConstant.ARTICLE_SORT_BY_FIELDS);
        }
        if (!(sortDirection.equalsIgnoreCase("asc") || sortDirection.equalsIgnoreCase("desc"))) {
            throw new ArticleException(HttpStatus.BAD_REQUEST.value(), RequestConstant.ORDER_BY_FIELDS);
        }
        if (!(filterBy.equals("category") || filterBy.equals("brand") || filterBy.equals("none"))) {
            throw new FilterException(HttpStatus.BAD_REQUEST.value(), RequestConstant.FILTER_BY_FIELDS);
        }
        if ((isCategoryFilter || filterBy.equals("brand"))
                && (filterValue.isBlank() || filterValue.equals("none") || filterValue.isEmpty())) {
            throw new FilterException(HttpStatus.BAD_REQUEST.value(),
                    String.format(RequestConstant.FILTER_VALUE_CANT_BE_NULL, "filterValue"));
        }
        if (isCategoryFilter) {
            try {
                Long.parseLong(filterValue);
            } catch (NumberFormatException e) {
                throw new FilterException(HttpStatus.BAD_REQUEST.value(),
                        String.format(RequestConstant.FILTER_VALUE_MUST_BE_NUMBER, "filterValue"));
            }
        }


    }
}
