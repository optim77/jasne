package com.example.dstay.main.Controller.Auth;

import com.example.dstay.main.Entity.User;
import com.example.dstay.main.Enums.Role;
import com.example.dstay.main.Repository.UserRepository;
import com.example.dstay.main.Security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
