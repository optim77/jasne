package com.example.dstay.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "roles")
@Data
@RestResource(rel = "role", path = "role")
public class Role extends BaseEntity{

    @Column(name = "role", unique = true, nullable = false, length = 50)
    private String name;


}
