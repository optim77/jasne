package com.example.dstay.DataMaker;

import com.example.dstay.Entity.Role;
import com.example.dstay.Entity.User;
import com.example.dstay.Repository.RoleRepository;
import com.example.dstay.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleMaker {

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    public void userCreator(){
        Role role = new Role();
        role.setName("USER");
        roleRepository.save(role);
    }

}
