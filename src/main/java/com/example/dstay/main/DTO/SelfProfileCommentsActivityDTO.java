package com.example.dstay.main.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class SelfProfileCommentsActivityDTO {

    private Long Id;
    private String content;
    private Date creationDate;
    private Integer votes;
}
