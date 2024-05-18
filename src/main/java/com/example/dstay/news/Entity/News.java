package com.example.dstay.news.Entity;

import com.example.dstay.categories.Entity.Category;
import com.example.dstay.main.Entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;

@Entity
@Data
@Table(name = "news")
@RestResource(rel = "element", path = "element")
public class News {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @Column(name = "Title", nullable = false, unique = false, length = 255)
    private String title;

    @Column(name = "url", nullable = true, unique = false, length = 255)
    private String URL;

    @Column(name = "description", nullable = true, unique = false, length = 10000)
    private String description;

    @Column(name = "created_at", nullable = false, unique = false)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "votes", nullable = true, unique = false)
    private int votes;

}
