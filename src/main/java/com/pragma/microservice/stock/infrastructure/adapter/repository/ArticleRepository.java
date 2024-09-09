package com.pragma.microservice.stock.infrastructure.adapter.repository;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.infrastructure.adapter.entity.ArticleEntity;
import com.pragma.microservice.stock.infrastructure.adapter.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> findByName(String name);
}
