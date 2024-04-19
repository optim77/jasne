package com.example.dstay.main.DataMaker;

import com.example.dstay.main.Entity.Role;
import com.example.dstay.main.Repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
