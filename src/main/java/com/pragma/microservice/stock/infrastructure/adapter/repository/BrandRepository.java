package com.pragma.microservice.stock.infrastructure.adapter.repository;

import com.pragma.microservice.stock.infrastructure.adapter.entity.BrandEntity;
import com.pragma.microservice.stock.infrastructure.adapter.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    List<BrandEntity> findByName(String name);
}
