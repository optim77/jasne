package com.example.dstay.main.Controller.User;

import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(produces = "application/json")
public class UserController {


    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile/{profileId}")
    public User getSelfProfile(@PathVariable Long profileId) throws Exception {
        return userRepository.findById(profileId).orElseThrow(Exception::new);
    }

    @PostMapping("/profile")
    public List<User> searchByName(@RequestBody String searchByName) {
        return new ArrayList<>(userRepository.findUsersByUsername(searchByName));
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
