package com.frun.api;

import com.frun.domain.service.FloodApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Scheduler {
    private final FloodApiService floodApiService;

    @Scheduled(fixedRate = 10000)
    public void schedule() {
        floodApiService.test();
    }
}
