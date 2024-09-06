package com.pragma.microservice.stock.infrastructure.rest.controller;

import com.pragma.microservice.stock.application.service.BrandService;
import com.pragma.microservice.stock.application.dto.request.BrandRequestDto;
import com.pragma.microservice.stock.application.dto.response.BrandResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.ErrorResponse;
import com.pragma.microservice.stock.infrastructure.swaggerConfig.BrandResponseListApiFormat;
import com.pragma.microservice.stock.infrastructure.swaggerConfig.BrandResponseUniqueApiFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@Tag(name = "Brand")
public class BrandController {
    private final BrandService brandService;
    @Operation(summary = "Get all brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the brands",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponseListApiFormat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})

    @GetMapping()
    public ApiResponseFormat<List<BrandResponseDto>> getAllBrands(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size,
            @RequestParam(value = "direction", defaultValue = "ASC" ) Sort.Direction direction) {
        return brandService.getAllBrand(page, size, direction);
    }
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @Operation(summary = "Create Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BrandResponseUniqueApiFormat.class)) }),
            @ApiResponse(responseCode = "409", description = "The brand 'nameBrand' already exist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "BrandException",summary = "Example response when the brand already exists",
                            value = "{ \"status\": 409, \"message\": \"The brand 'Electronics' already exists.\" }" ))}),
    })
    @PostMapping()
    public ApiResponseFormat<BrandResponseDto> createBrand(@Valid @RequestBody BrandRequestDto brandRequest) {
        return brandService.createBrand(brandRequest);
    }
}
