package com.example.dstay.comments.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CommentWithNewsDTO extends CommentDTO{

    private String newsTitle;
    private Integer commentVotes;
    private Integer newsVotes;
    private Long authorNews;

}
