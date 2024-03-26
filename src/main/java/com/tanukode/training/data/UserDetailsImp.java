package com.tanukode.training.data;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserDetailsImp implements UserDetails{
    private final UUID uid;
    private final String username;
    private final String password;
    private final String email;
    private final String firstName;
    private final String lastName;
    @JsonProperty("authorities")
    private final Collection<Authority> authorities;
    private final boolean accountNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean credentialsNonExpired = true;
    private final boolean enabled = true;
}

// sample json for signup
// {
//     "username": "kakita",
//     "password": "test",
//     "email": "testasdfasdfasdf",
//     "firstName": "test",
//     "lastName": "test",
//     "authorities": [
//         "ROLE_USER",
//         "ROLE_ADMIN"
//     ]
// }