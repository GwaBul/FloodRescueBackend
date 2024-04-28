package com.frun.domain.service;

import com.frun.domain.interfaces.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
}
