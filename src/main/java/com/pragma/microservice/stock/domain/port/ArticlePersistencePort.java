package com.pragma.microservice.stock.domain.port;

import com.pragma.microservice.stock.domain.model.Article;

public interface ArticlePersistencePort {
    Article createCategory(Article article);
}
