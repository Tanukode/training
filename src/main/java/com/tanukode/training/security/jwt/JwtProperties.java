package com.tanukode.training.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

    private String secretKey = "@$fdgsfghna22hjkg1da$&&/hh***/treEWQq32praisethesun@$fdgsfghna22hjkg1da$&&/hh***/treEWQq32";

    // validity in milliseconds
    private long validityInMs = 3600000; // 1h

}