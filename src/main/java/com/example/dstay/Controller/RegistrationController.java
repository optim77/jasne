package com.example.dstay.Controller;

import com.example.dstay.DTO.LoginDTO;
import com.example.dstay.DTO.RegisterDTO;
import com.example.dstay.Entity.User;
import com.example.dstay.Repository.UserRepository;
import com.example.dstay.Security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@Slf4j
@BasePathAwareController
@RepositoryRestController
@CrossOrigin(origins = "*")
public class RegistrationController {

//    private final UserRepository userRepository;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    private final PasswordEncoder passwordEncoder;
//
//    private final JwtUtils jwtUtils;
//
//
//    public RegistrationController(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
//        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtUtils = jwtUtils;
//    }
//
//    @PostMapping(value = "/register", path = "/register", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<User> processRegistration(@RequestBody RegisterDTO registerDTO){
//        if(userRepository.existsByEmail(registerDTO.getEmail())){
//            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
//        }
//        try{
//            User user = new User();
//            user.setEmail(registerDTO.getEmail());
//            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
//            String username = registerDTO.getEmail().split("@")[0];
//            user.setUsername(username);
//            user.setVerified(false);
//            User savedUser = userRepository.save(user);
//            return ResponseEntity.ok(savedUser);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.PARTIAL_CONTENT);
//        }
//    }

//    @PostMapping(value = "/login", path = "/login", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> authenticateUser(@RequestBody UserDetails userDetails) {
//        try{
//            Authentication authentication = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return new ResponseEntity<>("123", HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
//        }
//    }
}
