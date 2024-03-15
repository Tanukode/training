package com.tanukode.training.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tanukode.training.data.User;
import com.tanukode.training.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository repository;

    @Autowired
    PasswordEncoder encoder;


    public Flux<User> getUsers() {
        return repository.findAll();
    }

    public Mono<User> getUserById(UUID uid) {
        return repository.findById(uid);
    }

    public Mono<User> saveUser(User user) {
        return repository.findByUsername(user.getUsername()).map(Optional::of)
        .defaultIfEmpty(Optional.empty())
        .flatMap(optionalUser->{
            if(optionalUser.isPresent()){
                return Mono.error(new Exception("Username already exists"));
            }else{
                User newUser = new User(null, user.getUsername(), encoder.encode(user.getPassword()), user.getEmail()
                , user.getFirstName(), user.getLastName());
                return repository.save(newUser);
            }
        });
    }

    public Mono<User> updateUser(User user){
        UUID uid = user.getUid();
        return repository.findById(uid).flatMap(
            existingUser ->{
                User newUser = new User(uid, user.getUsername(), encoder.encode(user.getPassword()), user.getEmail()
                , user.getFirstName(), user.getLastName());
                return repository.save(newUser);
            }
        ).switchIfEmpty(Mono.error(new Exception("User not found")));
    }

    public Mono<String> deleteUser(UUID uid) {
        return repository.deleteById(uid).then(Mono.just("User deleted successfully"));
    }
}
