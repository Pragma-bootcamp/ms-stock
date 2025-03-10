package com.pragma.microservice.stock.domain.port;

import com.pragma.microservice.stock.domain.model.Article;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;

import java.util.List;

public interface ArticlePersistencePort {
    Article createArticle(Article article);
    ApiResponseFormat<List<Article>> getAllArticles(Integer page,Integer size, String sortBy,String sortDirection,
                                                    String filterBy,String filterValue);
}
