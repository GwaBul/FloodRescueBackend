package com.frun.application;

import com.frun.domain.model.Token;
import com.frun.domain.service.TokenService;
import com.frun.domain.service.WeatherAlertService;
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

    public void runProgram() {
        if(! weatherAlertService.isAlert()) {
//            log.info("기상특보 없음"); // TODO: 추후 삭제
            return;
        }
        // 토큰 목록 조회
        List<Token> tokenList = tokenService.fetchTokenList();
        // FCM으로 모든 장치에 GPS 요청을 브로드캐스트
        // if 하천 내부이면, 장치에 탈출구 좌표 리스트 전송
    }
}
