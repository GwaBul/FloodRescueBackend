package com.frun.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frun.domain.service.TokenService;
import com.frun.domain.service.WeatherAlertService;
import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public String push() {
        WeatherAlertService.WeatherAlert devAlert = WeatherAlertService.WeatherAlert
                .builder()
                .alerted(true)
                .cities(List.of("구미시"))
                .build();

        String json;
        try {
            json = objectMapper.writeValueAsString(devAlert);

            // 토큰 목록 조회
            List<String> tokens = tokenService.fetchTokenList();
            // Message 생성 (특보 지역명 리스트)
            MulticastMessage messages = MulticastMessage.builder()
                    .addAllTokens(tokens)
                    .setNotification(Notification.builder()
                            .setTitle("제목")
                            .setBody("푸시 알림이 왔어요.")
                            .build())
                    .putData("push", "푸쉬")
                    .build();

            BatchResponse response = messaging.sendEachForMulticast(messages);
            log.info("전송 실패 개수 : {}", response.getFailureCount());
        } catch (JsonProcessingException e) {
            log.error("json 변환 실패", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류");
        } catch (FirebaseMessagingException e) {
            log.error("전송 실패", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류");
        }

        return "전송 성공";
    }
}
