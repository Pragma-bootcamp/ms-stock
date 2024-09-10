package com.pragma.microservice.stock.infrastructure.rest.controller;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.application.dto.response.article.ArticleResponseDto;
import com.pragma.microservice.stock.application.service.ArticleService;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public ApiResponseFormat<List<ArticleResponseDto>> getAllArticles(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            @RequestParam(value = "filterBy", defaultValue = "none") String filterBy,
            @RequestParam(value = "filterValue", defaultValue = "none") String filterValue) {
        return articleService.getAllArticles(page, size, sortBy, sortDir.toUpperCase(),
                filterBy, filterValue);
    }
    @PostMapping()
    public ApiResponseFormat<ArticleResponseDto> createBrand(@Valid @RequestBody ArticleRequestDto articleRequest) {
        return articleService.createArticle(articleRequest);
    }
}
