package com.tanukode.training.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tanukode.training.service.UserService;

@Component
public class AuthenticationHandler {
    @Autowired
    UserService userService;

    // public Mono<ServerResponse> signUp(ServerRequest request) {
    //     return request.bodyToMono(UserDetailsImp.class)
    //             .flatMap(userDetails -> {
    //                 User newUser = new User(null, userDetails.getUsername(), userDetails.getPassword(),
    //                         userDetails.getEmail(), userDetails.getFirstName(), userDetails.getLastName());
    //                 return userService.saveUser(newUser)
    //                         .then();

    //             });
    // }
}
