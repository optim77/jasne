package com.example.dstay.main.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class SelfProfileNewsActivityDTO {

    private Long Id;
    private String title;
    private Date creationDate;
    private Integer votes;
}
