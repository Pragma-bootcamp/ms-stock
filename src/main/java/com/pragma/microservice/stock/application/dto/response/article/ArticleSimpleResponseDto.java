package com.pragma.microservice.stock.application.dto.response.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleSimpleResponseDto {
    private Long id;
    private String name;

    public ArticleSimpleResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
