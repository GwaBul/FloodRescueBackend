package com.frun.api;

import com.frun.api.request.TokenRequest;
import com.frun.api.response.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {
    @PostMapping
    public ResponseEntity<TokenResponse.Save> saveToken(@RequestBody TokenRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
          TokenResponse.Save.builder().result("토큰 저장 성공").build()
        );
    }
}
