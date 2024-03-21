package com.tanukode.training.data;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Table("authority")
public class CustomAuthority {
    @Column("id")
    private final Long id;
    @Column("name")
    private final String name;
    
}
