package com.example.dstay.Entity;

import com.example.dstay.Enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "user")
@Data
@RestResource(rel = "user", path = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 25)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "password", nullable = false, unique = false, length = 255)
    private String password;

    @Column(name = "bio", nullable = true, unique = false, length = 500)
    private String bio;

    @Column(name = "created_at", nullable = false, unique = false)
    private Date createdAt;

    @PrePersist
    private void createdAt(){
        this.createdAt = new Date();
    }

    @Column(name = "verified", nullable = false)
    private Boolean verified;

    @OneToMany(mappedBy = "id")
    private Set<Element> recordSet;

    @ElementCollection
    @CollectionTable(name = "match")
    private List<Long> matches;

    @Column(name = "latitude", unique = false, nullable = true)
    private Double latitude;

    @Column(name = "longitude", unique = false, nullable = true)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername(){
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
