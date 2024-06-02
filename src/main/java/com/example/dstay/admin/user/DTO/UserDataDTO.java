package com.example.dstay.admin.user.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDataDTO extends AdminAuthDTO{
    private Long user_id;
}
