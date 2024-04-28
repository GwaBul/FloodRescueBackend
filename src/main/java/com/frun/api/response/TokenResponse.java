package com.frun.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TokenResponse {
    @Builder @Getter
    @AllArgsConstructor @NoArgsConstructor
    public static class Save {
        private String result;
    }
}
