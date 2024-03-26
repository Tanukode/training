package com.tanukode.training.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Login {
    private final String username;
    private final String password;
}
