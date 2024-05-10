package com.example.dstay.main.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class ServiceSelfProfileDTO {

    private String bio;
    private String name;
    private String surname;
    private String username;
    private String specialization;

}
