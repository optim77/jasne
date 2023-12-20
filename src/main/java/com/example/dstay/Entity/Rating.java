package com.example.dstay.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rating")
@Data
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rating", nullable = false, unique = false, length = 1)
    private int rating;
    //rate by who

    //what rate
}
