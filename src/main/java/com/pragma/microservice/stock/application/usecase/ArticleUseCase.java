package com.pragma.microservice.stock.application.usecase;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.application.dto.response.ArticleResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;

public interface ArticleUseCase {
    public ApiResponseFormat<ArticleResponseDto> createArticle(ArticleRequestDto articleRequestDto);
}
