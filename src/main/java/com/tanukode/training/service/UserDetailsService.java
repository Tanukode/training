package com.tanukode.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tanukode.training.data.UserDetailsImp;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements ReactiveUserDetailsService {
    @Autowired
    private DatabaseClient databaseClient;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return findUserDetailsByUsername(username).cast(UserDetails.class);
    }

    public Mono<UserDetailsImp> findUserDetailsByUsername(String username) {
        return databaseClient.sql("""
                SELECT u.uid, u.username, u.password, u.email, u.first_name, u.last_name, STRING_AGG(a.name, ',') as authorities
                FROM "user" u
                JOIN "user_authorities" ua ON u.uid = ua.user_uid
                JOIN "authority" a ON ua.authority_id = a.id
                WHERE u.username = :username
                GROUP BY u.uid, u.username, u.password, u.email, u.first_name, u.last_name
                """).bind("username", username)
                .mapProperties(UserDetailsImp.class).one();
    }


}
