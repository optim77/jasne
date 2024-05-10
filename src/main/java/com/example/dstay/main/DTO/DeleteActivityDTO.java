package com.example.dstay.main.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DeleteActivityDTO {
    private Long id;
    private String token;
}
