package com.example.dstay.comments.DTO;

import com.example.dstay.main.Entity.User;
import lombok.Data;
import com.example.dstay.news.Entity.News;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class CommentDTO {
    private Long comment_id;
    private Long news_id;
    private String content;
    private String token;
}
