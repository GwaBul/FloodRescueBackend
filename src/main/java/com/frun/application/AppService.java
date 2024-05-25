package com.frun.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frun.domain.service.TokenService;
import com.frun.domain.service.WeatherAlertService;
import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppService {
    private final WeatherAlertService weatherAlertService;
    private final TokenService tokenService;
    private final ObjectMapper objectMapper;
    private final FirebaseMessaging messaging;

    public void runProgram() {
        // 기상 특보 감지
        WeatherAlertService.WeatherAlert alert = weatherAlertService.isAlert();
        if(! alert.getAlerted()) {
            log.info("기상특보 없음");
            return;
        }

        // 토큰 목록 조회
        List<String> tokens = tokenService.fetchTokenList();
        // Message 생성
        // todo: 특보 발령 도시 리스트 포함하여 보내기
        MulticastMessage messages = MulticastMessage.builder()
                .addAllTokens(tokens)
                .putData("push", "푸쉬")
                .build();
        // Gps 요청 브로드캐스트
        try {
            BatchResponse response = messaging.sendEachForMulticast(messages);
            log.info("전송 실패 개수 : {}", response.getFailureCount());
        } catch (FirebaseMessagingException e) {
            log.error("전송 실패", e.getMessage());
        }
    }
}
