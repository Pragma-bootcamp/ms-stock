package com.pragma.microservice.stock.infrastructure.adapter.mapper;

import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.infrastructure.adapter.entity.BrandEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface BrandDboMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "articles",ignore = true)
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    BrandEntity toDbo(Brand brand);

    @InheritInverseConfiguration
    Brand toDomain(BrandEntity entity);
}
