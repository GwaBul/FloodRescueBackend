package com.frun.api;

import com.frun.api.request.GpsRequest;
import com.frun.api.response.GpsResponse;
import com.frun.application.GpsAppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GpsController {
    private final GpsAppService gpsAppService;

    @PostMapping("/gps")
    public GpsResponse.Send getGps(@RequestBody GpsRequest.Send request) {
        return gpsAppService.processGps(request);
    }
}
