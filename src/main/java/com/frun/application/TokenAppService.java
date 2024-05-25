package com.frun.application;

import com.frun.api.response.TokenResponse;
import com.frun.domain.model.Token;
import com.frun.domain.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenAppService {
    private final TokenService tokenService;

    public TokenResponse.Registration registerToken(String token) {
        Token savedToken = tokenService.registerToken(token);

        return TokenResponse.Registration.of(savedToken);
    }
}
