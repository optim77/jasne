package com.example.dstay.admin;

import com.example.dstay.main.Enums.Role;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;

public class AdminUtils {

    public static boolean adminRoleCheck(String token, UserRepository userRepository) {
        try {
            JwtUtils jwtUtils = new JwtUtils();
            String username = jwtUtils.extractUsername(token);
            return userRepository.findByUsername(username).getRole() == Role.ADMIN;
        } catch (Exception e) {
            return false;
        }
    }
}
