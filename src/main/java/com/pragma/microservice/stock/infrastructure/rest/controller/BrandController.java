package com.pragma.microservice.stock.infrastructure.rest.controller;

import com.pragma.microservice.stock.application.service.BrandService;
import com.pragma.microservice.stock.domain.model.dto.request.BrandRequestDto;
import com.pragma.microservice.stock.domain.model.dto.request.CategoryRequestDto;
import com.pragma.microservice.stock.domain.model.dto.response.BrandResponseDto;
import com.pragma.microservice.stock.domain.model.dto.response.CategoryResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.ErrorResponse;
import com.pragma.microservice.stock.infrastructure.swaggerConfig.BrandResponseApiFormat;
import com.pragma.microservice.stock.infrastructure.swaggerConfig.CategoryResponseApiFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@Tag(name = "Brand")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @Operation(summary = "Create Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponseApiFormat.class)) }),
            @ApiResponse(responseCode = "409", description = "The brand 'nameBrand' already exist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "BrandException",summary = "Example response when the brand already exists",
                            value = "{ \"status\": 409, \"message\": \"The brand 'Electronics' already exists.\" }" ))}),
    })
    @PostMapping()
    public ApiResponseFormat<BrandResponseDto> createCategory(@Valid @RequestBody BrandRequestDto brandRequest) {
        return brandService.createBrand(brandRequest);
    }
}
