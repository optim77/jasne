package com.example.dstay.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Set;

@Entity
@Table(name = "category")
@Data
@RestResource(rel = "category", path = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, length = 255)
    private String name;

    @OneToMany(mappedBy = "id")
    private Set<Element> records;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }


}
