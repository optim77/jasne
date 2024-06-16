package com.example.dstay.main.Repository;

import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Security.JwtUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String searchByName);
    List<User> findUsersByUsername(String searchByName);
    User findByUsernameOrEmail(String username, String email);
    boolean existsByEmail(String email);
    boolean deleteByUsernameOrEmail(JwtUtils jwtUtils, JwtUtils jwtUtils1);

    Page<User> findAll(Pageable pageable);

}
