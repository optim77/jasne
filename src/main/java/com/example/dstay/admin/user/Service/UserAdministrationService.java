package com.example.dstay.admin.user.Service;

import com.example.dstay.admin.AdminUtils;
import com.example.dstay.admin.user.DTO.UserDataDTO;
import com.example.dstay.admin.user.DTO.UsersListDTO;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Enums.Role;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAdministrationService {

    private final UserRepository userRepository;

    public UserAdministrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UsersListDTO> execGetUsersList(String token, Pageable pageable){
        if(AdminUtils.adminRoleCheck(token, userRepository)){
            try{
                Page<User> users =  userRepository.findAll(pageable);
                return new PageImpl<>(users.stream()
                        .map(n -> {
                            UsersListDTO usersListDTO = new UsersListDTO();
                            usersListDTO.setUsername(n.getUsername());
                            usersListDTO.setSurname(n.getSurname());
                            usersListDTO.setRole(n.getRole());
                            usersListDTO.setSpecialization(n.getSpecialization());
                            usersListDTO.setName(n.getName());
                            usersListDTO.setEnable(n.isEnable());
                            usersListDTO.setCreated_at(n.getCreatedAt());
                            usersListDTO.setId(n.getId());
                            return usersListDTO;
                        }).collect(Collectors.toList()),pageable, users.getTotalElements());
            }catch (Exception e){
                return new PageImpl<>(Collections.emptyList(), pageable, 0);
            }

        }
        return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }

    public ResponseEntity<Integer> execToggleBlockUser(UserDataDTO userDataDTO){
        if(AdminUtils.adminRoleCheck(userDataDTO.getToken(), userRepository)){
            Optional<User> user = userRepository.findById(userDataDTO.getUser_id());
            if(user.isPresent()){
                if (user.get().isEnable()){
                    user.get().setEnable(false);
                    userRepository.save(user.get());
                    return ResponseEntity.ok(1);
                }else {
                    user.get().setEnable(true);
                    userRepository.save(user.get());
                    return ResponseEntity.ok(0);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
