package com.pragma.microservice.stock.application.dto.response.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySimpleResponseDto {
    private Long id;
    private String name;

    public CategorySimpleResponseDto(String name, Long id) {
        this.name = name;
        this.id = id;
    }

}
