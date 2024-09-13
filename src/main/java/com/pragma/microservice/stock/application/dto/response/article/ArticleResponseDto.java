package com.pragma.microservice.stock.application.dto.response.article;

import com.pragma.microservice.stock.application.dto.response.brand.BrandSimpleResponseDto;
import com.pragma.microservice.stock.application.dto.response.category.CategorySimpleResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDto {
    private Long id;
    private String name;
    private String description;
    private Set<CategorySimpleResponseDto> categories;
    private BrandSimpleResponseDto brand;
    private Integer amount;
    private Double price;

    public ArticleResponseDto(Long id, String name, String description, Set<CategorySimpleResponseDto> categories,
                              Integer amount, Double price, BrandSimpleResponseDto brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.categories = categories;
        this.amount = amount;
        this.price = price;
    }

}
