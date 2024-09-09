package com.pragma.microservice.stock.infrastructure.adapter;

import com.pragma.microservice.stock.domain.exception.CategoryException;
import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.domain.model.constant.BrandConstant;
import com.pragma.microservice.stock.domain.port.BrandPersistencePort;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;
import com.pragma.microservice.stock.infrastructure.adapter.entity.BrandEntity;
import com.pragma.microservice.stock.infrastructure.adapter.mapper.BrandDboMapper;
import com.pragma.microservice.stock.infrastructure.adapter.repository.BrandRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BrandSpringJpaAdapter implements BrandPersistencePort {
    private final BrandRepository brandRepository;
    private final BrandDboMapper brandDboMapper;

    public BrandSpringJpaAdapter(BrandRepository brandRepository, BrandDboMapper brandDboMapper) {
        this.brandRepository = brandRepository;
        this.brandDboMapper = brandDboMapper;
    }

    @Override
    public Brand createBrand(Brand brand) {
        List<BrandEntity> existingBrand = brandRepository.findByName(brand.getName());
        if (!existingBrand.isEmpty()) {
            throw new CategoryException(HttpStatus.CONFLICT.value(),String.format(BrandConstant.BRAND_ALREADY_EXISTS_MESSAGE_ERROR,brand.getName()));
        }
        BrandEntity brandToSave = brandDboMapper.toDbo(brand);
        BrandEntity savedBrand = brandRepository.save(brandToSave);
        return brandDboMapper.toDomain(savedBrand);
    }

    @Override
    public ApiResponseFormat<List<Brand>> getAllBrands(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BrandEntity> brandPage = brandRepository.findAll(pageable);
        List<Brand> brands= brandPage
                .stream()
                .map(brandDboMapper::toDomain).toList();
        MetadataResponse meta = new MetadataResponse(page,brandPage.getTotalElements(),brandPage.getTotalPages(),size);
        return new ApiResponseFormat<>(brands,meta);
    }
}
