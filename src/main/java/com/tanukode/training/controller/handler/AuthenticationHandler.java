package com.tanukode.training.controller.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tanukode.training.data.Authority;
import com.tanukode.training.data.User;
import com.tanukode.training.data.UserDetailsImp;
import com.tanukode.training.service.UserDetailsService;
import com.tanukode.training.service.UserService;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationHandler {
    @Autowired
    UserService userService;
    @Autowired
    UserDetailsService userDetailsService;

    public Mono<ServerResponse> signup(ServerRequest request){
        return request.bodyToMono(UserDetailsImp.class)
                .flatMap(userDetails->{
                    User newUser = new User(null, userDetails.getUsername(), userDetails.getPassword(),
                            userDetails.getEmail(), userDetails.getFirstName(), userDetails.getLastName());
                    Collection<Authority> authorities = userDetails.getAuthorities();
                    if (authorities == null) {
                        authorities = new ArrayList<>();
                        authorities.add(new Authority("ROLE_USER"));
                    }
                    return userDetailsService.signUp(newUser, authorities).then(ok().body(userDetailsService.findByUsername(newUser.getUsername()), UserDetailsImp.class));
                });
    }
}
