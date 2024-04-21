package com.example.dstay.news.DTOs;

import com.example.dstay.news.Entity.Categories;
import com.example.dstay.news.Entity.News;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewsWithAuthorDetails {
    private Long newsId = null;
    private String newsTitle = null;
    private String newsUrl = null;
    private String newsDescription = null;
    private Date newsCreatedAt = null;
    private Integer newsVotes = null;
    private Categories categories = null;

    private Long authorId = null;
    private String authorName = null;
    private String authorSurname = null;
    private String authorSpecialization = null;
    private Date authorCreatedAt = null;

}
