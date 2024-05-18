package com.example.dstay.categories.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateCategoryDTO {
    private String name;
    private String image;
    private String description;
}
