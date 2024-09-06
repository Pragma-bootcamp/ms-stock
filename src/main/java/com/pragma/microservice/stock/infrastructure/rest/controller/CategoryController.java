package com.pragma.microservice.stock.infrastructure.rest.controller;

import com.pragma.microservice.stock.application.service.CategoryService;
import com.pragma.microservice.stock.application.dto.request.CategoryRequestDto;
import com.pragma.microservice.stock.application.dto.response.CategoryResponseDto;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.ErrorResponse;
import com.pragma.microservice.stock.infrastructure.swaggerConfig.CategoryResponseListApiFormat;
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
@RequestMapping("/categories")
@Tag(name = "Category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the categories",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseListApiFormat.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})})

    @GetMapping()
    public ApiResponseFormat<List<CategoryResponseDto>> getAllCategoriesSortedByName(
            @RequestParam(value = "page", defaultValue = "0" ,required = true) int page,
            @RequestParam(value = "size", defaultValue = "5", required = true) int size,
            @RequestParam(value = "direction", defaultValue = "ASC" ) Sort.Direction direction) {
        return categoryService.getAllCategories(page, size, direction);
    }
    @Operation(summary = "Create category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CategoryResponseListApiFormat.class)) }),
            @ApiResponse(responseCode = "409", description = "The category already exist",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class), examples = @ExampleObject(name = "CategoryException",summary = "Example response when the category already exists",
                            value = "{ \"status\": 409, \"message\": \"The category 'Electronics' already exists.\" }" ))}),
})
    @PostMapping()
    public ApiResponseFormat<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }
}
