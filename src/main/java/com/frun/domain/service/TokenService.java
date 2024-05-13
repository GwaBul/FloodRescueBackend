package com.frun.domain.service;

import com.frun.domain.interfaces.TokenRepository;
import com.frun.domain.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public List<Token> fetchTokenList() {
        return tokenRepository.findAll();
    }
}
