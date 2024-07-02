package com.example.dstay.main.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class DstaySecurityConfiguration {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private final AuthenticationProvider authenticationProvider;

    private final String[] whiteList = {
            "/**",
            "/register",
            "/authenticate",
            "/categories",
            "/authentication"
    };
    private final String[] forbiddenList = {
            "/element/**",
            "/profile/**",
            "/role/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(req -> req
                        .requestMatchers(whiteList).permitAll()
                        .requestMatchers(forbiddenList).authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/v1/auth/logout"));

        http.headers().frameOptions().disable();
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
        return http.build();
    }
}
