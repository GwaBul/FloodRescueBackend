package com.frun.api.response;

import com.frun.domain.model.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class TokenResponse {
    @Builder
    @Getter
    @AllArgsConstructor
    public static class Registration {
        private String token;

        public static Registration of(Token token) {
            return Registration.builder()
                    .token(token.getToken())
                    .build();
        }
    }
}
