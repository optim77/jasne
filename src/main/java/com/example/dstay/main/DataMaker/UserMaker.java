package com.example.dstay.main.DataMaker;

import com.example.dstay.main.Entity.User;

import com.example.dstay.main.Enums.Role;
import com.example.dstay.main.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserMaker {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    public void userCreator(){
        User user = new User();
        user.setUsername("Sew7n");
        user.setEmail("plajerowy@gmail.com");
        user.setPassword(passwordEncoder.encode("123123"));
        user.setBio("lorem ipsum");
        user.setLatitude(5.12);
        user.setLongitude(9.15);
        user.setCreatedAt(new Date());
        user.setVerified(true);
        user.setRole(Role.ADMIN);
        userRepository.save(user);
    }

}
