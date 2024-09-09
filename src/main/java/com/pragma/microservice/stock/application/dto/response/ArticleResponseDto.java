package com.pragma.microservice.stock.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
public class ArticleResponseDto {
    private Long id;
    private String name;
    private String description;
    private Set<CategoryResponseDto> categories;
    private Integer amount;
    private Double price;

    public ArticleResponseDto(Long id, String name, String description, Set<CategoryResponseDto> categories, Integer amount, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.amount = amount;
        this.price = price;
    }

}
