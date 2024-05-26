package com.frun.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frun.domain.service.TokenService;
import com.frun.domain.service.WeatherAlertService;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DevController {
    private final ObjectMapper objectMapper;
    private final FirebaseMessaging messaging;
    private final TokenService tokenService;

    // Data 메세지 전송 API
    @GetMapping("/push")
    public ResponseEntity<String> push() {
        WeatherAlertService.WeatherAlert alert = WeatherAlertService.WeatherAlert.builder()
                .alerted(true)
                .cities(List.of("대구광역시", "구미시"))
                .build();

        // 토큰 목록 조회
        List<String> tokens = tokenService.fetchTokenList();

        // cities 리스트를 JSON 문자열로 변환
        String citiesJson;
        try {
            citiesJson = objectMapper.writeValueAsString(alert.getCities());
        } catch (JsonProcessingException e) {
            log.error("도시 리스트를 JSON으로 변환하는데 실패했습니다.", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("도시 리스트를 JSON으로 변환하는데 실패했습니다.");
        }

        // Message 생성
        MulticastMessage messages = MulticastMessage.builder()
                .addAllTokens(tokens)
                .putData("cities", citiesJson)
                .build();

        // Gps 요청 브로드캐스트
        try {
            BatchResponse response = messaging.sendEachForMulticast(messages);
            log.info("전송 실패 개수 : {}", response.getFailureCount());
        } catch (FirebaseMessagingException e) {
            log.error("전송 실패", e.getMessage());
        }

        return ResponseEntity.ok("푸시 전송 성공");
    }
}
