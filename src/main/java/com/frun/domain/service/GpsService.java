package com.frun.domain.service;

import com.frun.api.request.GpsRequest;
import com.frun.domain.interfaces.RiverRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GpsService {
    private RiverRepository riverRepository;

    public IsInRiver isInRiver(GpsRequest.Send request) {
        /*
        if(! isInRiver)
            isInRiver 가 false,
            riverNo 가 null인 객체반환
        else
            isInRiver 가 true,
            riverNo 가 해당 하천의 PK인 객체반환
         */

    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class IsInRiver {
        private Boolean isInRiver;
        private Long riverNo;
    }
}
