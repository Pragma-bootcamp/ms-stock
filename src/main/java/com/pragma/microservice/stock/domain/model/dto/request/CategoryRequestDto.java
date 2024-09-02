package com.pragma.microservice.stock.domain.model.dto.request;

import com.pragma.microservice.stock.domain.model.constant.CategoryConstant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class CategoryRequestDto {
    @NotNull(message = CategoryConstant.CATEGORY_NAME_CANNOT_BE_NULL)
    @Size(max = CategoryConstant.MAX_NAME_LENGTH,message = CategoryConstant.CATEGORY_NAME_MUST_HAVE_LENGTH)
    private String name;
    @NotNull(message = CategoryConstant.CATEGORY_DESCRIPTION_CANNOT_BE_NULL)
    @Size(max = CategoryConstant.MAX_DESCRIPTION_LENGTH,message = CategoryConstant.CATEGORY_DESCRIPTION_MUST_HAVE_LENGTH)
    private String description;

    public CategoryRequestDto(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public CategoryRequestDto() {
    }
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
