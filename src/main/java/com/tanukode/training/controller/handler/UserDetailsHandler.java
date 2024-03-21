package com.tanukode.training.controller.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tanukode.training.service.UserDetailsService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserDetailsHandler {
    @Autowired
    private final UserDetailsService userDetailsService;

    public Mono<ServerResponse> getUserDetails(ServerRequest request){
        return userDetailsService.findUserDetailsByUsername(request.pathVariable("username"))
                .flatMap(userDetails -> ServerResponse.ok().bodyValue(userDetails))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}


