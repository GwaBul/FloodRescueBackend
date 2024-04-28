package com.frun.api;

import com.frun.api.request.GpsRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GpsController {
    @PostMapping("/gps")
    public String getGps(@RequestBody GpsRequest.Send request) {
        log.info(request.getX() + " , " + request.getY());

        return request.getX() + " , " + request.getY();
    }
}
