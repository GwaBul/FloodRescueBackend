package com.frun.api.request;

import lombok.Getter;

public class TokenRequest {
    @Getter
    public static class Registration {
        private String token;
    }
}
