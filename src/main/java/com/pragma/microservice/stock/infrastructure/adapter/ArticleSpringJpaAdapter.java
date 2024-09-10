package com.pragma.microservice.stock.infrastructure.adapter;

import com.pragma.microservice.stock.domain.exception.CategoryException;
import com.pragma.microservice.stock.domain.model.Article;
import com.pragma.microservice.stock.domain.model.constant.ArticleConstant;
import com.pragma.microservice.stock.domain.model.constant.CategoryConstant;
import com.pragma.microservice.stock.domain.port.ArticlePersistencePort;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;
import com.pragma.microservice.stock.infrastructure.adapter.entity.ArticleEntity;
import com.pragma.microservice.stock.infrastructure.adapter.entity.CategoryEntity;
import com.pragma.microservice.stock.infrastructure.adapter.mapper.ArticleDboMapper;
import com.pragma.microservice.stock.infrastructure.adapter.repository.ArticleRepository;
import com.pragma.microservice.stock.infrastructure.adapter.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleSpringJpaAdapter implements ArticlePersistencePort {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleDboMapper articleDboMapper;

    public ArticleSpringJpaAdapter(ArticleRepository articleRepository, CategoryRepository categoryRepository,
                                   ArticleDboMapper articleDboMapper) {
        this.articleRepository = articleRepository;
        this.articleDboMapper = articleDboMapper;
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
    public ApiResponseFormat<List<Article>> getAllArticles(Integer page, Integer size, String sortBy,
                                                           String sortDirection, String filterBy, String filterValue) {
        Sort.Direction dir = Objects.equals(sortDirection, "DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));
        Page<ArticleEntity> articlePage = switch (filterBy.toLowerCase()) {
            case "category" -> {
                Long categoryId = Long.parseLong(filterValue);
                yield articleRepository.findByCategoryId(categoryId, pageable);
            }
            case "brand" -> articleRepository.findByBrandName(filterValue, pageable);
            default -> articleRepository.findAll(pageable);
        };
        List<Article> articles = articlePage.getContent().stream().map(articleDboMapper::toDomain).toList();
        MetadataResponse meta = new MetadataResponse(page, articlePage.getTotalElements(), articlePage.getTotalPages(),
                size);
        return new ApiResponseFormat<>(articles, meta);
    }
}
