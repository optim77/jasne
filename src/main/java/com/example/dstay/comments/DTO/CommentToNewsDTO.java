package com.example.dstay.comments.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class CommentToNewsDTO {
    private Long news_id;
    private Long author_news_id;
    private Long author_comment_id;
    private String author_comment_name;
    private String comment_content;
    private Date comment_create_at;
    private Integer votes;
}
