package com.tanukode.training.controller.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tanukode.training.data.User;
import com.tanukode.training.service.UserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

    @Autowired
    private final UserService userService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ok().body(userService.getUsers(), User.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        return userService.getUserById(UUID.fromString(request.pathVariable("uid")))
                .flatMap(user -> ok().bodyValue(user))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return ok()
                .body(
                        request.bodyToMono(User.class)
                                .flatMap(userService::saveUser),
                        User.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        UUID uid = UUID.fromString(request.pathVariable("uid"));
        Mono<User> userMono = request.bodyToMono(User.class).flatMap(user -> {
            User newUser = new User(
                    uid, user.getUsername(), user.getPassword(), user.getEmail(),
                    user.getFirstName(), user.getLastName());

            return userService.updateUser(newUser);
        });

        return ok().body(userMono, User.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ServerResponse> delete(ServerRequest request){
        UUID uid = UUID.fromString(request.pathVariable("uid"));
        return ok().body(userService.deleteUser(uid), User.class);
        
    }

}
