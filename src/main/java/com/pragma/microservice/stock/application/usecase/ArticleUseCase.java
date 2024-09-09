package com.pragma.microservice.stock.application.usecase;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.application.dto.response.ArticleResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ArticleUseCase {
    ApiResponseFormat<ArticleResponseDto> createArticle(ArticleRequestDto articleRequestDto);
    ApiResponseFormat<List<ArticleResponseDto>> getAllArticles(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
}
