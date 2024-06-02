package com.example.dstay.admin.user.Controller;

import com.example.dstay.admin.user.DTO.UserDataDTO;
import com.example.dstay.admin.user.Service.UserAdministrationService;
import com.example.dstay.main.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
@RestResource
public class UserAdministrationController {

    private final UserAdministrationService userAdministrationService;

    public UserAdministrationController(UserRepository userRepository, UserAdministrationService userAdministrationService) {
        this.userAdministrationService = userAdministrationService;
    }

    @PostMapping("/admin/block")
    public ResponseEntity<HttpStatus> toggleBlockUser(@RequestBody UserDataDTO userDataDTO) {
        return userAdministrationService.execToggleBlockUser(userDataDTO);
    }
}
