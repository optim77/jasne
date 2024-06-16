package com.example.dstay.admin.user.DTO;

import com.example.dstay.main.Enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class UsersListDTO {

    private Long id;
    private Role role;
    private String username;
    private Date created_at;
    private boolean enable;
    private String name;
    private String surname;
    private String specialization;

}
