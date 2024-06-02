package com.example.dstay.admin.user.Service;

import com.example.dstay.admin.AdminUtils;
import com.example.dstay.admin.user.DTO.UserDataDTO;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Enums.Role;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class UserAdministrationService {

    private final UserRepository userRepository;

    public UserAdministrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<HttpStatus> execToggleBlockUser(UserDataDTO userDataDTO){
        if(AdminUtils.adminRoleCheck(userDataDTO.getToken(), userRepository)){
            Optional<User> user = userRepository.findById(userDataDTO.getUser_id());
            if(user.isPresent()){
                if (user.get().isEnable()){
                    user.get().setEnable(false);
                }else {
                    user.get().setEnable(true);
                }
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
