package com.example.dstay.categories.Entity;

import com.example.dstay.main.Entity.Element;
import com.example.dstay.news.Entity.News;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Set;

@Entity
@Data
@Table(name = "category")
@RestResource(rel = "category", path = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255, unique = true)
    private String name;

    @Column(name = "description", nullable = true, length = 255, unique = false)
    private String description;

    @Column(name = "news_counter", nullable = true)
    private Integer news_counter;

    @Column(name = "image", unique = false, length = 255, nullable = true)
    private String image;

    @OneToMany(mappedBy = "id")
    private Set<News> newsSet;
}
