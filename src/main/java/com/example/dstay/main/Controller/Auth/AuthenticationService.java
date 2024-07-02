package com.example.dstay.main.Controller.Auth;

import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Enums.Role;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){
        boolean isExist = userRepository.existsByEmail(request.getEmail());
        if(!isExist){
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setUsername(request.getEmail());
            user.setVerified(false);
            user.setRole(Role.USER);
            userRepository.save(user);
            String token = jwtUtils.generateToken(user);
            return AuthenticationResponse.builder().token(token).build();
        }else{
            return AuthenticationResponse.builder().token("").build();
        }

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

    public AuthenticationResponse adminAuthenticate(AuthenticationRequest authenticationRequest) throws AccessDeniedException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()));
        User user = userRepository.findByUsernameOrEmail(authenticationRequest.getEmail(), authenticationRequest.getEmail());
        if (user.getRole().equals(Role.ADMIN)){
            String token = jwtUtils.generateToken(user);
            return AuthenticationResponse.builder().token(token).build();
        }else {
            throw new AccessDeniedException("Access denied: User does not have admin privileges.");
        }

    }
}
