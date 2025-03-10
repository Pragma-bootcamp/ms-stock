package com.pragma.microservice.stock.infrastructure.adapter.repository;

import com.pragma.microservice.stock.infrastructure.adapter.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findByName(String name);
}