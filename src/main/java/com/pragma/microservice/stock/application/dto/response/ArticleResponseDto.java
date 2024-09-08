package com.pragma.microservice.stock.application.dto.response;

import java.util.Set;

public class ArticleResponseDto {
    private Long id;
    private String name;
    private String description;
    private Set<CategoryResponseDto> categories;

    public ArticleResponseDto(Long id, String name, String description, Set<CategoryResponseDto> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<CategoryResponseDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryResponseDto> categories) {
        this.categories = categories;
    }
}
