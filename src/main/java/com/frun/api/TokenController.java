package com.frun.api;

import com.frun.api.request.TokenRequest;
import com.frun.api.response.TokenResponse;
import com.frun.application.TokenAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final TokenAppService tokenAppService;

    @PostMapping("/tokens")
    public TokenResponse.Registration saveToken(@RequestBody TokenRequest.Registration request) {
        if(request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "요청 body 가 null 입니다.");
        }
        if(request.getToken() == null || request.getToken().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰은 null 또는 빈 값이 될 수 없습니다");
        }

        return tokenAppService.registerToken(request.getToken());
    }
}
