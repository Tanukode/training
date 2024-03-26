package com.tanukode.training.service;

import org.springframework.stereotype.Service;

import com.tanukode.training.data.Login;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public Mono<Void> LogIn(Login login){
        return Mono.empty();
    }
}
