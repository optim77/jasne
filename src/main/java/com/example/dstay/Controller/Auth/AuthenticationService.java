package com.example.dstay.Controller.Auth;

import com.example.dstay.Entity.User;
import com.example.dstay.Enums.Role;
import com.example.dstay.Repository.UserRepository;
import com.example.dstay.Security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getEmail());
        user.setVerified(false);
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        String token = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        User user = userRepository.findByUsernameOrEmail(authenticationRequest.getEmail(), authenticationRequest.getEmail());
        String token = jwtUtils.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
}
