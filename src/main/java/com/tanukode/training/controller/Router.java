package com.tanukode.training.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.tanukode.training.controller.handler.UserHandler;

@Configuration
public class Router {
    @Bean
    RouterFunction<ServerResponse> route(UserHandler handler) {
        return RouterFunctions.route()
                .GET("/users", handler::getAll)
                .GET("/users/{uid}", handler::getById)
                .POST("/users", handler::save)
                .PUT("/users/{uid}", handler::update)
                .DELETE("/users/{uid}", handler::delete)
                .build();
    }
}
