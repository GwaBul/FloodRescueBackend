package com.frun.domain.service;

import com.frun.domain.interfaces.TokenRepository;
import com.frun.domain.model.Token;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    @Transactional
    public List<String> fetchTokenList() {
        return tokenRepository.findAll()
                .stream()
                .map(Token::getToken)
                .toList();
    }

    @Transactional
    public Token registerToken(String token) {
        Token newToken = Token.builder()
                .token(token)
                .build();

        return tokenRepository.save(newToken);
    }
}
