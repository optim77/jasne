package com.example.dstay.main.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity{

    @Column(name = "role", unique = true, nullable = false, length = 50)
    private String name;


}
