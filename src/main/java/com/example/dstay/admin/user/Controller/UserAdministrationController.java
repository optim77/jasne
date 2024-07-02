package com.example.dstay.admin.user.Controller;

import com.example.dstay.admin.user.DTO.AdminAuthDTO;
import com.example.dstay.admin.user.DTO.UserDataDTO;
import com.example.dstay.admin.user.DTO.UsersListDTO;
import com.example.dstay.admin.user.Service.UserAdministrationService;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@RestResource
public class UserAdministrationController {

    private final UserAdministrationService userAdministrationService;

    public UserAdministrationController(UserRepository userRepository, UserAdministrationService userAdministrationService) {
        this.userAdministrationService = userAdministrationService;
    }

    @PostMapping("admin/users")
    public Page<UsersListDTO> getUsersList(@RequestBody AdminAuthDTO adminAuthDTO, Pageable pageable){
        return userAdministrationService.execGetUsersList(adminAuthDTO.getToken(), pageable);
    }

    @PostMapping("/admin/block")
    public ResponseEntity<Integer> toggleBlockUser(@RequestBody UserDataDTO userDataDTO) {
        return userAdministrationService.execToggleBlockUser(userDataDTO);
    }



}
