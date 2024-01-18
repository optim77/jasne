package com.example.dstay.Security;

import com.example.dstay.Service.User.UserRepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DstaySecurityConfiguration {

    @Autowired
    private final UserRepositoryUserDetailsService userDetailsService;

    public DstaySecurityConfiguration(UserRepositoryUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(encoder());
//    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws  Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/api").permitAll()
                .anyRequest()
                .permitAll();

        http.csrf().disable();
        http.cors().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}
