package com.example.dstay.Repository;

import com.example.dstay.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String searchByName);
    List<User> findUsersByUsername(String searchByName);

    User findByUsernameOrEmail(String username, String email);
    boolean existsByEmail(String email);
}
