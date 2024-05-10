package com.example.dstay.main.DTO;

import com.example.dstay.comments.Entity.Comment;
import com.example.dstay.news.Entity.News;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Data
public class UserActivityNewsDTO {

    private Page<SelfProfileNewsActivityDTO> news;
}
