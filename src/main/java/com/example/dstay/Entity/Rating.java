package com.example.dstay.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rating")
@Data
public class Rating extends BaseEntity{


    @Column(name = "rating", nullable = false, unique = false, length = 1)
    private int rating;
    //rate by who

    //what rate
}
