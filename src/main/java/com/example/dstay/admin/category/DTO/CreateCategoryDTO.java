package com.example.dstay.admin.category.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateCategoryDTO {
    private Long id;
    private String name;
    private String image;
    private String description;
    private String token;
}
