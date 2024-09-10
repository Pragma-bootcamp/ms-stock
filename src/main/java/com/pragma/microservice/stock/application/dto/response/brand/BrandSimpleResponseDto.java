package com.pragma.microservice.stock.application.dto.response.brand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandSimpleResponseDto {
    private Long id;
    private String name;

    public BrandSimpleResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
