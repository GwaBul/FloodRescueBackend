package com.frun.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class FloodApiService {
    private void manipulateFromApiResponse(String rawData) {
        // 원시데이터에서 row로 분리하기
        List<String> rows = Arrays.stream(rawData.split("\n"))
                .filter(str -> !str.startsWith("#")) // #으로 시작하지 않는 문자열만 필터링
                // 시 | 군 | 도로 끝나는 지역만 필터링
                .filter(str -> str.split(",")[3].endsWith("시"))
                .filter(str -> str.split(",")[3].endsWith("군"))
                .filter(str -> str.split(",")[3].endsWith("도"))
                .toList();
//        rows.forEach(log::info);
        // 각 row을 RegionForcastInfo로 변환
        // Region 배열 반환
    }

    public void test() {
        log.info("test 호출");
        WebClient webClient = WebClient.builder().build(); // WebClient 객체 생성
        String url = "http://apihub.kma.go.kr/api/typ01/url/wrn_now_data_new.php?fe=f&tm=&disp=0&help=1&authKey=ou_rPt79T3av6z7e_R92fw";

        Mono<String> res = webClient.get() // webClient.method(HttpMethod.GET)
                .uri(url).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
        String rawData = res.block();
        log.info(rawData);
        manipulateFromApiResponse(rawData);
    }
}