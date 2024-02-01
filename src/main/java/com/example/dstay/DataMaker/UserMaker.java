package com.example.dstay.DataMaker;

import com.example.dstay.Entity.Role;
import com.example.dstay.Entity.User;

import com.example.dstay.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserMaker {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void userCreator(){
        User user = new User();
        user.setUsername("Q");
        user.setEmail("n@n.com");
        user.setPassword("123");
        user.setBio("lorem ipsum");
        user.setLatitude(5.12);
        user.setLongitude(9.15);
        user.setCreatedAt(new Date());
        user.setVerified(true);
        userRepository.save(user);
    }

}
