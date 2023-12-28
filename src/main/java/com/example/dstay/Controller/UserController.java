package com.example.dstay.Controller;

import com.example.dstay.DTO.LoginDTO;
import com.example.dstay.Entity.User;
import com.example.dstay.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping(produces = "application/json")
public class UserController {


    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/profile/{profileId}")
    public User getSelfProfile(@PathVariable Long profileId) throws Exception {
        return userRepository.findById(profileId).orElseThrow(Exception::new);
    }

    @PostMapping("/profile/{searchByName}")
    public List<User> searchByName(@PathVariable String searchByName) {
        return new ArrayList<>(userRepository.findUsersByUsername(searchByName));
    }


}
