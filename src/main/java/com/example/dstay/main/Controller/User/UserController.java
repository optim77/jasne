package com.example.dstay.main.Controller.User;

import com.example.dstay.main.DTO.*;
import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import com.example.dstay.main.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class UserController {


    private final UserRepository userRepository;
    private final UserService userService;


    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/profile")
    public ResponseEntity<ServiceSelfProfileDTO> getSelfProfile(@RequestBody SelfProfileDTO token) {
        return userService.execGetSelfProfile(token);
    }

    @PutMapping("/profile")
    public ResponseEntity<HttpStatus> updateProfile(@RequestBody UpdateServiceSelfProfileDTO user){
        return userService.execUpdateProfile(user);
    }

    @PostMapping ("/profile/news/activity")
    public ResponseEntity<UserActivityNewsDTO> userNewsActivity(@RequestBody SelfProfileDTO token, Pageable pageable){
        return userService.execUserNewsActivity(token, pageable);
    }

    @PostMapping ("/profile/comments/activity")
    public ResponseEntity<UserActivityCommentsDTO> userCommentsActivity(@RequestBody SelfProfileDTO token, Pageable pageable){
        return userService.execUserCommentsActivity(token, pageable);
    }

    @PostMapping("/profile/news/delete")
    public ResponseEntity<HttpStatus> userNewsActivityDelete(@RequestBody DeleteActivityDTO deleteActivityDTO){
        return userService.execUserNewsActivityDelete(deleteActivityDTO);
    }

    @PostMapping("/profile/comments/delete")
    public ResponseEntity<HttpStatus> userCommentsActivityDelete(@RequestBody DeleteActivityDTO deleteActivityDTO){
        return userService.execUserCommentsActivityDelete(deleteActivityDTO);
    }

    @GetMapping("/profile/delete")
    public ResponseEntity<HttpStatus> deleteProfile(){
        JwtUtils jwtUtils = new JwtUtils();
        HttpServletRequest request = null;
        String authHeader = request.getHeader("Authorization");
        jwtUtils.extractUsername(authHeader);
        if (userRepository.deleteByUsernameOrEmail(jwtUtils, jwtUtils)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

}
