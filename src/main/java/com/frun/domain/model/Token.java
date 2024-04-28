package com.frun.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "token")
public class Token {
    @Id @GeneratedValue
    private Long id;
    private String token;
}
