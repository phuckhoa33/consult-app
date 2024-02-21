package com.consult_app.demo.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class User implements UserDetails {
    private long userId;
    private String email;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String avatar;
    private String phoneNumber;
    private String dateOfBirth;
    private boolean gender;
    private boolean ban;
    private String address;
    private Date createdAt;
    private Date updatedAt;
    private List<String> roles = new ArrayList<>();

    public User() {
        roles.add("USER");

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
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
