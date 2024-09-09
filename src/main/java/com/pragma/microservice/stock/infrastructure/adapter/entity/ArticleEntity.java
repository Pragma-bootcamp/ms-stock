package com.pragma.microservice.stock.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer amount;
    private Double price;
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "article_category",
            joinColumns =  @JoinColumn(name = "article_id", referencedColumnName = "id") ,
            inverseJoinColumns = @JoinColumn(name = "categori_id", referencedColumnName = "id") )
    private Set<CategoryEntity> categories;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
