package com.example.dstay.Controller;

import com.example.dstay.DTO.LoginDTO;
import com.example.dstay.DTO.RegisterDTO;
import com.example.dstay.Entity.User;
import com.example.dstay.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
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
@BasePathAwareController
@RepositoryRestController
@CrossOrigin(origins = "*")
public class RegistrationController {

    private final UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping(value = "/register", path = "/register", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> processRegistration(@RequestBody RegisterDTO registerDTO){
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
        try{
            User user = new User();
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            String username = registerDTO.getEmail().split("@")[0];
            user.setUsername(username);
            user.setVerified(false);
            User savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(value = "/login", path = "/login", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto) {
        try{
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("400", HttpStatus.UNAUTHORIZED);
        }
    }
}
