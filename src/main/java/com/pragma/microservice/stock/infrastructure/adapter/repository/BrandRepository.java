package com.pragma.microservice.stock.infrastructure.adapter.repository;

import com.pragma.microservice.stock.infrastructure.adapter.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    List<BrandEntity> findByName(String name);
}
