package com.example.dstay.comments.DTO;

import com.example.dstay.main.Entity.User;
import lombok.Data;
import com.example.dstay.news.Entity.News;

import java.util.Date;

@Data
public class CommentDTO {

    public News comment_to_news;
    public User author;
    public String content;
    private Date creationDate;
}
