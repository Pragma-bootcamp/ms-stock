package com.pragma.microservice.stock.domain.port;
import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import java.util.List;

public interface BrandPersistencePort {
    Brand createBrand (Brand brand);
    ApiResponseFormat<List<Brand>> getAllBrands (int page, int size);
    Brand getBrand (Long id);
}