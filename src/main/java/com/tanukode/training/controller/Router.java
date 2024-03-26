package com.tanukode.training.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tanukode.training.controller.handler.AuthenticationHandler;
import com.tanukode.training.controller.handler.UserDetailsHandler;
import com.tanukode.training.controller.handler.UserHandler;

@Configuration
public class Router {
    @Bean
    RouterFunction<ServerResponse> route(UserHandler handler, UserDetailsHandler userDetailsHandler, AuthenticationHandler authenticationHandler) {
        return RouterFunctions.route()
                .GET("/details/{username}", userDetailsHandler::getUserDetails)
                .GET("/users", handler::getAll)
                .GET("/users/{uid}", handler::getById)
                .POST("/users", handler::save)
                .POST("/signup", authenticationHandler::signup)
                .POST("/login", authenticationHandler::login)
                .PUT("/users/{uid}", handler::update)
                .DELETE("/users/{uid}", handler::delete)
                .build();
    }
}

