package com.frun.api;

import com.frun.application.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Scheduler {
    private final AppService appService;

//    @Scheduled(fixedRate = 1000 * 60 * 5) // 5분마다 실행
//    void runProgram() {
//        appService.runProgram();
//    }
}
