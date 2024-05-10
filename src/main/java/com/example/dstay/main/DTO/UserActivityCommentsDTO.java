package com.example.dstay.main.DTO;

import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.news.Entity.News;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Data
public class UserActivityCommentsDTO {

    private Page<SelfProfileCommentsActivityDTO> comments;
}
