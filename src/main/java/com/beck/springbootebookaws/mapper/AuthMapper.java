package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.auth.AuthResponse;
import com.beck.springbootebookaws.db.entity.Role;
import com.beck.springbootebookaws.db.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AuthMapper {

    public AuthResponse view(String token, String message, User user) {
        var authResponse = new AuthResponse();
        if (user != null) {
            setAuthority(authResponse, user.getRoles());
        }
        authResponse.setJwtToken(token);
        authResponse.setFirstName(user.getFirstName());
        authResponse.setEmail(user.getEmail());
        authResponse.setMessage(message);
        return authResponse;
    }

    private void setAuthority(AuthResponse authResponse, List<Role> roles) {
        Set<String> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(role.getName());
        }
        authResponse.setAuthorities(authorities);
    }
}
