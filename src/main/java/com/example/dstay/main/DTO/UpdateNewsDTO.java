package com.example.dstay.main.DTO;

import com.example.dstay.news.DTOs.NewsDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UpdateNewsDTO extends NewsDTO {

    private Long id;
}
