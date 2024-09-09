package com.pragma.microservice.stock.infrastructure.rest.controller;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.application.dto.request.BrandRequestDto;
import com.pragma.microservice.stock.application.dto.response.ArticleResponseDto;
import com.pragma.microservice.stock.application.dto.response.BrandResponseDto;
import com.pragma.microservice.stock.application.service.ArticleService;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleCategory {
    private ArticleService articleService;

    public ArticleCategory(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping()
    public ApiResponseFormat<ArticleResponseDto> createBrand(@Valid @RequestBody ArticleRequestDto articleRequest) {
        return articleService.createArticle(articleRequest);
    }
}
