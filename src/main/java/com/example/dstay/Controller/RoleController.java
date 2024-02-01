package com.example.dstay.Controller;

import com.example.dstay.Entity.Role;
import com.example.dstay.Repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping(produces = "application/json")
public class RoleController {

    public final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostMapping(value = "/api/admin/setRole")
    public void createRole(@RequestBody String role){
        Role role1 = new Role();
        role1.setName(role);
        roleRepository.save(role1);
    }
}
