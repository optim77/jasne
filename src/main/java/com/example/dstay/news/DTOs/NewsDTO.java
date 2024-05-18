package com.example.dstay.news.DTOs;

import com.example.dstay.main.Entity.User;
import lombok.Data;
import com.example.dstay.news.Entity.Categories;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NewsDTO {

    public String author;
    public String URL;
    public String description;
    public Long categories;
    public String title;
}
