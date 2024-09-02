package com.pragma.microservice.stock.domain.model.dto.request;

import com.pragma.microservice.stock.domain.model.constant.BrandConstant;
import com.pragma.microservice.stock.domain.model.constant.CategoryConstant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BrandRequestDto {
    @NotNull(message = BrandConstant.BRAND_NAME_CANNOT_BE_NULL)
    @Size(max = BrandConstant.MAX_NAME_LENGTH,message = BrandConstant.BRAND_NAME_MUST_HAVE_LENGTH)
    private String name;
    @NotNull(message = BrandConstant.BRAND_DESCRIPTION_CANNOT_BE_NULL)
    @Size(max = BrandConstant.MAX_DESCRIPTION_LENGTH,message = BrandConstant.BRAND_DESCRIPTION_MUST_HAVE_LENGTH)
    private String description;

    public BrandRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public BrandRequestDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
