package com.pragma.microservice.stock.application.dto.request;

import com.pragma.microservice.stock.domain.exception.ArticleException;
import com.pragma.microservice.stock.domain.model.constant.ArticleConstant;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor

public class ArticleRequestDto {
    @NotNull(message = ArticleConstant.ARTICLE_NAME_CANNOT_BE_NULL)
    @NotEmpty(message = ArticleConstant.ARTICLE_NAME_CANNOT_BE_NULL)
    @Size(max = ArticleConstant.MAX_NAME_LENGTH,message = ArticleConstant.ARTICLE_NAME_MUST_HAVE_LENGTH)
    private String name;
    @NotNull(message = ArticleConstant.ARTICLE_DESCRIPTION_CANNOT_BE_NULL)
    @NotEmpty(message = ArticleConstant.ARTICLE_DESCRIPTION_CANNOT_BE_NULL)
    @Size(max = ArticleConstant.MAX_DESCRIPTION_LENGTH,message = ArticleConstant.ARTICLE_DESCRIPTION_MUST_HAVE_LENGTH)
    private String description;
    @NotNull(message = ArticleConstant.ARTICLE_AMOUNT_CANNOT_BE_NULL)
    @Negative(message = ArticleConstant.ARTICLE_AMOUNT_CANNOT_BE_NEGATIVE)
    private Integer amount;
    @NotNull(message = ArticleConstant.ARTICLE_PRICE_CANNOT_BE_NULL)
    @Negative(message = ArticleConstant.ARTICLE_PRICE_CANNOT_BE_NEGATIVE)
    private Double price;
    @NotNull(message = ArticleConstant.ARTICLE_CATEGORIES_CANNOT_BE_NULL)
    @NotEmpty(message = ArticleConstant.ARTICLE_CATEGORIES_CANNOT_BE_NULL)
    private Set<Long> categories;
    @NotNull(message = ArticleConstant.BRAND_CANNOT_BE_NULL)
    private Long brand;

    public void validate(){
        if(this.getCategories().isEmpty()) {
            throw new ArticleException(HttpStatus.BAD_REQUEST.value(), ArticleConstant.CATEGORIES_MUST_HAVE_LEAST_ONE);
        }
        if (new HashSet<Long>(this.getCategories()).size() != this.getCategories().size()) {
            throw new ArticleException(HttpStatus.BAD_REQUEST.value(), ArticleConstant.CATEGORIES_CANT_BE_DUPLICATED);
        }
    }

}
