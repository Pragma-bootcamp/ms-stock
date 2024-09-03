package com.pragma.microservice.stock.infrastructure.adapter;

import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.domain.port.BrandPersistencePort;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BrandSpringJpaAdapter implements BrandPersistencePort {
    @Override
    public Brand createBrand(Brand brand) {
        return null;
    }

    @Override
    public ApiResponseFormat<List<Brand>> getAllBrands(int page, int size) {
        return null;
    }
}
