package com.example.dstay.admin;

import com.example.dstay.main.Enums.Role;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;

import java.util.Objects;

public class AdminUtils {

    public static boolean adminRoleCheck(String token, UserRepository userRepository) {
        try {
            JwtUtils jwtUtils = new JwtUtils();
            return Objects.equals(jwtUtils.extractRole(token), Role.ADMIN.toString());
        } catch (Exception e) {
            return false;
        }
    }
}
