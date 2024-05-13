package com.frun.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@Slf4j
public class WeatherAlertService {
    @Value("${apikey}")
    private String apiKey;

    public boolean isAlert() {
        log.info("isAlert() 실행");
        try {
            String rawData = requestApi();
            log.info(rawData); // TODO: 추후 삭제
            return Arrays.stream(rawData.split("\n"))
                    .filter(str -> !str.startsWith("#"))
                    .filter(str -> {
                        String trimmed = str.split(",")[3].trim();
                        return trimmed.endsWith("시") || trimmed.endsWith("군") || trimmed.endsWith("구");
                        //|| trimmed.endsWith("읍") || trimmed.endsWith("면");
                    })
                    .peek(log::info) // TODO: 추후 삭제
                    .anyMatch(str -> {
                        String trimmed = str.split(",")[6].trim();
                        return trimmed.equals("강풍") || trimmed.equals("호우");
                    });
        } catch(Exception e) {
            log.error("기상특보 조회 중 오류가 발생했습니다", e);
            return false;
        }
    }

    private String requestApi() {
        WebClient webClient = WebClient.builder().build();
        String url = "http://apihub.kma.go.kr/api/typ01/url/wrn_now_data_new.php?fe=f&tm=&disp=0&help=1&authKey=" + apiKey;
        Mono<String> res = webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

        return res.block();
    }
}