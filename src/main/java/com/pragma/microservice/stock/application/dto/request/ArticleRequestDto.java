package com.pragma.microservice.stock.application.dto.request;

import java.util.Set;

public class ArticleRequestDto {
    private String name;
    private String description;
    private Set<String> categories;

    public ArticleRequestDto(String name, String description, Set<String> categories) {
        this.name = name;
        this.description = description;
        this.categories = categories;
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

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }
}
