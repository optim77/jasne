package com.example.dstay.votes.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class RateDTO {
    private Long news_id;
    private Long comment_id;
    private String token;
}
