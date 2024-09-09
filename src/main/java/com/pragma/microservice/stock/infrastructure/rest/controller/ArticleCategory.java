package com.pragma.microservice.stock.infrastructure.rest.controller;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.application.dto.response.ArticleResponseDto;
import com.pragma.microservice.stock.application.service.ArticleService;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.infrastructure.utils.ArticleSortByEnum;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleCategory {
    private ArticleService articleService;

    public ArticleCategory(ArticleService articleService) {
        this.articleService = articleService;
    }
    @GetMapping()
    public ApiResponseFormat<List<ArticleResponseDto>> getAllArticles(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sortBy") ArticleSortByEnum sortBy,
            @RequestParam(value = "sortDir", defaultValue = "ASC" ) Sort.Direction sortDir) {
        return articleService.getAllArticles(page, size,sortBy.getValue(), sortDir.name());
    }
    @PostMapping()
    public ApiResponseFormat<ArticleResponseDto> createBrand(@Valid @RequestBody ArticleRequestDto articleRequest) {
        return articleService.createArticle(articleRequest);
    }
}
