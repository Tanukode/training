package com.tanukode.training.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.tanukode.training.data.CustomAuthority;

import reactor.core.publisher.Mono;

public interface AuthorityRepository extends ReactiveCrudRepository<CustomAuthority, Integer>{
    public Mono<CustomAuthority> findByName(String name);
}
