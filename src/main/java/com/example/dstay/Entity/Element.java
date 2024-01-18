package com.example.dstay.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "record")
@Data
@RestResource(rel = "element", path = "element")
public class Element extends BaseEntity{

    @Column(name = "name", nullable = false, unique = false, length = 255)
    private String name;

    @Column(name = "description", nullable = false, unique = false, length = 3000)
    private String description;

    @Column(name = "source", nullable = false, unique = false, length = 1000)
    private String source;

    @Column(name = "image", nullable = false, unique = false, length = 500)
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private User author;
}
