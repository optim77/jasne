package com.example.dstay.news.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class CategoryNewsDTO {

    private String title;
    private Date createdAt;
    private Long news_id;
    private Integer votes;
    private String author_username;
    private Long author_id;

}
