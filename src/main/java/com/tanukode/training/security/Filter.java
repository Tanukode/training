package com.tanukode.training.security;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Filter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("hello from filter");
        exchange.getResponse()
                .getHeaders().add("web-filter", "web-filter-test");
        return chain.filter(exchange);
    }

}
