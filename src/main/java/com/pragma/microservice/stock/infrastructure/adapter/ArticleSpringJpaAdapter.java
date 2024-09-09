package com.pragma.microservice.stock.infrastructure.adapter;

import com.pragma.microservice.stock.domain.exception.CategoryException;
import com.pragma.microservice.stock.domain.model.Article;
import com.pragma.microservice.stock.domain.model.constant.ArticleConstant;
import com.pragma.microservice.stock.domain.model.constant.CategoryConstant;
import com.pragma.microservice.stock.domain.port.ArticlePersistencePort;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.infrastructure.adapter.entity.ArticleEntity;
import com.pragma.microservice.stock.infrastructure.adapter.entity.CategoryEntity;
import com.pragma.microservice.stock.infrastructure.adapter.mapper.ArticleDboMapper;
import com.pragma.microservice.stock.infrastructure.adapter.mapper.CategoryDboMapper;
import com.pragma.microservice.stock.infrastructure.adapter.mapper.CategoryDboMapperImpl;
import com.pragma.microservice.stock.infrastructure.adapter.repository.ArticleRepository;
import com.pragma.microservice.stock.infrastructure.adapter.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleSpringJpaAdapter implements ArticlePersistencePort {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleDboMapper articleDboMapper;
    private final CategoryDboMapper categoryDboMapper;

    public ArticleSpringJpaAdapter(ArticleRepository articleRepository,CategoryRepository categoryRepository, ArticleDboMapper articleDboMapper, CategoryDboMapper categoryDboMapper) {
        this.articleRepository = articleRepository;
        this.articleDboMapper = articleDboMapper;
        this.categoryDboMapper = categoryDboMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Article createArticle(Article article) {
        List<ArticleEntity> existingArticle = articleRepository.findByName(article.getName());
        if (!existingArticle.isEmpty()) {
            throw new CategoryException(HttpStatus.CONFLICT.value(),
                    String.format(ArticleConstant.ARTICLE_ALREADY_EXISTS_MESSAGE_ERROR, article.getName()));
        }
        ArticleEntity articleToSave = articleDboMapper.toDbo(article);
        Set<CategoryEntity> managedCategories = article.getCategories().stream()
                .map(category -> categoryRepository.findById(category.getId())
                        .orElseThrow(() -> new CategoryException(HttpStatus.BAD_REQUEST.value(),
                                CategoryConstant.CATEGORY_NOT_FOUND)))
                .collect(Collectors.toSet());
        articleToSave.setCategories(managedCategories);
        ArticleEntity savedArticle = articleRepository.save(articleToSave);
        return articleDboMapper.toDomain(savedArticle);
    }

    @Override
    public ApiResponseFormat<List<Article>> getAllArticles(Integer size, Integer page, String direction) {
        return null;
    }
}
