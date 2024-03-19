package com.tanukode.training.data;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Table("authority")
public class Authority implements GrantedAuthority{
    @Column("name")
    private final String authority;


    @Override
    public String getAuthority() {
        return this.authority;
    }
    
}