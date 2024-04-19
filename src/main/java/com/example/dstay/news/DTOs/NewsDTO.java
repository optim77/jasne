package com.example.dstay.news.DTOs;

import com.example.dstay.main.Entity.User;
import lombok.Data;
import com.example.dstay.news.Entity.Categories;

@Data
public class NewsDTO {

    public String author;
    public String URL;
    public String description;
    public Categories categories;
    public String title;

}
