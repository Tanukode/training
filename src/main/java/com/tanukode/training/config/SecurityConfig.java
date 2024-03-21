package com.tanukode.training.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/login").permitAll()
                .pathMatchers("/signup").permitAll()
                .anyExchange().authenticated())
                .csrf(csrf->csrf.disable())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

}
