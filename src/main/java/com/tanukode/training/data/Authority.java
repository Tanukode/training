package com.tanukode.training.data;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Authority implements GrantedAuthority {
    @JsonProperty("authority")
    @Column("name")
    private final String authority;
    @Override
    public String getAuthority() {
        return this.authority;
    }

}
