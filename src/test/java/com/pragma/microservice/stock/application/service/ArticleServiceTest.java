package com.pragma.microservice.stock.application.service;

import com.pragma.microservice.stock.application.dto.request.ArticleRequestDto;
import com.pragma.microservice.stock.application.dto.request.constant.RequestConstant;
import com.pragma.microservice.stock.application.dto.response.article.ArticleResponseDto;
import com.pragma.microservice.stock.application.exception.FilterException;
import com.pragma.microservice.stock.application.exception.PaginationException;
import com.pragma.microservice.stock.application.mapper.article.ArticleRequestDtoMapper;
import com.pragma.microservice.stock.application.mapper.article.ArticleResponseDtoMapper;
import com.pragma.microservice.stock.application.usecase.ArticleUseCase;
import com.pragma.microservice.stock.domain.exception.ArticleException;
import com.pragma.microservice.stock.domain.model.Article;
import com.pragma.microservice.stock.domain.model.Brand;
import com.pragma.microservice.stock.domain.model.Category;
import com.pragma.microservice.stock.domain.model.constant.ArticleConstant;
import com.pragma.microservice.stock.domain.port.ArticlePersistencePort;
import com.pragma.microservice.stock.domain.port.BrandPersistencePort;
import com.pragma.microservice.stock.domain.port.CategoryPersistencePort;
import com.pragma.microservice.stock.domain.utils.ApiResponseFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class ArticleServiceTest {
    @Mock
    private ArticleUseCase articleUseCase;
    @Mock
    private ArticlePersistencePort articlePersistencePort;
    @Mock
    private CategoryPersistencePort categoryPersistencePort;
    @Mock
    private BrandPersistencePort brandPersistencePort;
    @Mock
    private ArticleRequestDtoMapper articleRequestDtoMapper;
    @Mock
    private ArticleResponseDtoMapper articleResponseDtoMapper;
    @InjectMocks
    private ArticleService articleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createArticle() {
        ArticleRequestDto articleRequestDto = mock(ArticleRequestDto.class);
        Article article = new Article();
        Brand brand = new Brand();
        Article articleCreated = new Article();
        when(articleRequestDtoMapper.toDomain(articleRequestDto)).thenReturn(article);
        when(brandPersistencePort.getBrand(any())).thenReturn(brand);
        when(articleRequestDto.getCategories()).thenReturn(List.of(1L, 2L));
        when(categoryPersistencePort.getCategory(anyLong())).thenReturn(new Category());
        when(articlePersistencePort.createArticle(any(Article.class))).thenReturn(articleCreated);
        when(articleResponseDtoMapper.toDto(articleCreated)).thenReturn(new ArticleResponseDto());
        ApiResponseFormat<ArticleResponseDto> response = articleService.createArticle(articleRequestDto);
        assertNotNull(response);
        verify(brandPersistencePort, times(1)).getBrand(any());
        verify(categoryPersistencePort, times(2)).getCategory(anyLong());
        verify(articlePersistencePort, times(1)).createArticle(article);
    }
    @Test
    void createArticleDuplicate() {
        ArticleRequestDto articleRequestDto = new ArticleRequestDto();
        articleRequestDto.setName("Test Article");
        articleRequestDto.setDescription("Test Description");
        articleRequestDto.setAmount(10);
        articleRequestDto.setPrice(100.0);
        articleRequestDto.setBrand(1L); // Id de la marca
        articleRequestDto.setCategories(List.of(1L, 2L)); // Ids de las categorías

        // Creamos la entidad Article simulada que se espera
        Article articleMock = new Article();
        articleMock.setName("Test Article");
        articleMock.setDescription("Test Description");
        articleMock.setAmount(10);
        articleMock.setPrice(100.0);
        Brand brandMock = new Brand();
        brandMock.setId(1L);
        // Creamos una entidad simulada para representar el artículo creado
        Article articleCreated = new Article();
        // Configuramos los mocks
        when(articleRequestDtoMapper.toDomain(articleRequestDto)).thenReturn(articleMock); // Simulamos el mapeo
        when(brandPersistencePort.getBrand(1L)).thenReturn(brandMock); // Simulamos obtener la marca
        when(categoryPersistencePort.getCategory(anyLong())).thenReturn(new Category()); // Simulamos obtener las categorías
        when(articlePersistencePort.createArticle(any(Article.class))).thenReturn(articleCreated); // Simulamos la creación del artículo
        // Act
        ApiResponseFormat<ArticleResponseDto> response = articleService.createArticle(articleRequestDto);
        // Assert
        assertNotNull(response);
        verify(articleRequestDtoMapper).toDomain(articleRequestDto); // Verificamos que el DTO fue mapeado
        assertEquals(articleRequestDto.getName(), articleMock.getName());
        assertEquals(articleRequestDto.getDescription(), articleMock.getDescription());
        assertEquals(articleRequestDto.getAmount(), articleMock.getAmount());
        assertEquals(articleRequestDto.getPrice(), articleMock.getPrice());
        verify(brandPersistencePort, times(1)).getBrand(articleRequestDto.getBrand()); // Verifica la asociación con la marca
        verify(categoryPersistencePort, times(2)).getCategory(anyLong());
    }
    @Test
    void createArticleMoreThanThreeCategories(){
        // Arrange
        ArticleRequestDto articleRequestDto = new ArticleRequestDto();
        articleRequestDto.setName("Test Article");
        articleRequestDto.setDescription("Test Description");
        articleRequestDto.setAmount(10);
        articleRequestDto.setPrice(100.0);
        articleRequestDto.setBrand(1L);
        // Intentamos agregar más de tres categorías
        articleRequestDto.setCategories(List.of(1L, 2L, 3L, 4L));
        // Act & Assert
        ArticleException exception = assertThrows(ArticleException.class, () -> {
            articleService.createArticle(articleRequestDto);
        });
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getErrorCode());
        assertEquals(ArticleConstant.CANNOT_ADD_MORE_CATEGORIES, exception.getMessage());
    }
    @Test
    void getArticles() {
        // Arrange
        int page = 0;
        int size = 10;
        String sortBy = "name";
        String sortDirection = "asc";
        String filterBy = "none";
        String filterValue = "";
        List<Article> articles = List.of(new Article(),new Article());
        ApiResponseFormat<List<Article>> allArticles = new ApiResponseFormat<>(articles, null);
        when(articlePersistencePort.getAllArticles(page, size, sortBy, sortDirection, filterBy, filterValue))
                .thenReturn(allArticles);
        when(articleResponseDtoMapper.toDto(any(Article.class))).thenReturn(new ArticleResponseDto());
        // Act
        ApiResponseFormat<List<ArticleResponseDto>> response = articleService.getAllArticles(page, size, sortBy, sortDirection, filterBy, filterValue);
        // Assert
        assertNotNull(response);
        assertEquals(2, response.getData().size());
        verify(articlePersistencePort, times(1)).getAllArticles(page, size, sortBy, sortDirection, filterBy, filterValue);
    }
    @Test
    void testValidatePagination_withInvalidPage() {
        Exception exception = assertThrows(PaginationException.class, () -> {
            articleService.validatePagination(-1, 10, "name", "asc", "none", "");
        });
        assertEquals("The params 'size' or 'page' mustn't be negative", exception.getMessage());
    }
    @Test
    void testValidatePagination_withInvalidSortBy() {
        Exception exception = assertThrows(FilterException.class, () -> {
            articleService.validatePagination(0, 10, "invalid", "asc", "none", "");
        });
        assertEquals("The field 'sortBy' only can be 'name' 'price' 'amount'", exception.getMessage());
    }

    @Test
    void testValidatePagination_withInvalidSortDirection() {
        Exception exception = assertThrows(ArticleException.class, () -> {
            articleService.validatePagination(0, 10, "name", "invalid", "none", "");
        });
        assertEquals("The param 'orderDir' must be 'asc' or 'desc'", exception.getMessage());
    }

    @Test
    void testValidatePagination_withInvalidFilterBy() {
        Exception exception = assertThrows(FilterException.class, () -> {
            articleService.validatePagination(0, 10, "name", "asc", "invalidFilter", "");
        });
        assertEquals(RequestConstant.FILTER_BY_FIELDS, exception.getMessage());
    }
}