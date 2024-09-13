package com.pragma.microservice.stock.application.service;

import com.pragma.microservice.stock.application.dto.request.BrandRequestDto;
import com.pragma.microservice.stock.application.dto.request.constant.RequestConstant;
import com.pragma.microservice.stock.application.dto.response.brand.BrandResponseDto;
import com.pragma.microservice.stock.application.exception.PaginationException;
import com.pragma.microservice.stock.application.mapper.brand.BrandRequestDtoMapper;
import com.pragma.microservice.stock.application.mapper.brand.BrandResponseDtoMapper;
import com.pragma.microservice.stock.domain.exception.BrandException;
import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.domain.port.BrandPersistencePort;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import com.pragma.microservice.stock.domain.utils.MetadataResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class BrandServiceTest {
    @Mock
    private BrandPersistencePort brandPersistencePort;
    @Mock
    private BrandRequestDtoMapper brandRequestDtoMapper;
    @Mock
    private BrandResponseDtoMapper brandResponseDtoMapper;
    @InjectMocks
    private BrandService brandService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createBrand() {
        BrandRequestDto brandRequestDto = mock(BrandRequestDto.class);
        Brand newBrand = new Brand();
        when((brandRequestDtoMapper.toDomain(brandRequestDto))).thenReturn(newBrand);
        when(brandPersistencePort.createBrand(any(Brand.class))).thenReturn(newBrand);
        when(brandResponseDtoMapper.toDto(newBrand)).thenReturn(new BrandResponseDto());
        ApiResponseFormat<BrandResponseDto> response = brandService.createBrand(brandRequestDto);
        assertNotNull(response);
    }

    @Test
    void createBrandEmptyFields() {
        BrandRequestDto brandRequestDto = new BrandRequestDto("","");
        Exception exception = assertThrows(BrandException.class, brandRequestDto::validateRequestDto);
        assertEquals("The field  \"name\" can't be null", exception.getMessage());
    }

    @Test
    void createBrandDescriptionEmptyField() {
        BrandRequestDto brandRequestDto = new BrandRequestDto("Hola Mundo","");
        Exception exception = assertThrows(BrandException.class, brandRequestDto::validateRequestDto);
        assertEquals("The field  \"description\" can't be null", exception.getMessage());
    }

    @Test
    void getAllBrandPaginationValidation() {
        Exception exceptionNegativePagination = assertThrows(PaginationException.class,()->{
            brandService.validatePagination(-5,5,"ASC");
        });
        assertEquals(RequestConstant.PAGINATION_CANNOT_BE_NEGATIVE, exceptionNegativePagination.getMessage());
        Exception exception = assertThrows(PaginationException.class,()->{
            brandService.validatePagination(0,5,"Invalid");
        });
        assertEquals(RequestConstant.ORDER_BY_FIELDS, exception.getMessage());
    }

    @Test
    void getAllBrand() {
        int page = 0;
        int size = 10;
        Sort.Direction sortDir = Sort.Direction.ASC;
        List<Brand> brands = List.of(new Brand(), new Brand());
        MetadataResponse metadata = new MetadataResponse(page,10L,1,size);
        ApiResponseFormat<List<Brand>> allBrands = new ApiResponseFormat<>(brands,metadata);
        when(brandPersistencePort.getAllBrands(page,size)).thenReturn(allBrands);
        when(brandResponseDtoMapper.toDto(any(Brand.class))).thenReturn(new BrandResponseDto());
        assertEquals(2,allBrands.getData().size());
    }

    @Test
    void getAllBrandNotNull() {
        int page = 0;
        int size = 5;
        Sort.Direction sortDir = Sort.Direction.DESC;
        List<Brand> brands = List.of(new Brand(), new Brand());
        ApiResponseFormat<List<Brand>> allBrands = new ApiResponseFormat<>(brands,new MetadataResponse());
        when(brandPersistencePort.getAllBrands(anyInt(), anyInt())).thenReturn(allBrands);
        when(brandResponseDtoMapper.toDto(any(Brand.class))).thenReturn(new BrandResponseDto());
        ApiResponseFormat<List<BrandResponseDto>> response = brandService.getAllBrand(page,size,sortDir);
        assertNotNull(response);
    }
}