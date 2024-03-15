package com.tanukode.training.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.tanukode.training.data.User;

import reactor.core.publisher.Mono;


@Repository
public interface UserRepository extends ReactiveCrudRepository<User, UUID>{
    public Mono<User> findByUsername(String username);
}
