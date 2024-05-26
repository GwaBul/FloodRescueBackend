package com.frun.domain.service;

import com.frun.api.request.GpsRequest;
import com.frun.domain.interfaces.RiverRepository;
import com.frun.domain.model.River;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GpsService {
    private final RiverRepository riverRepository;

    public IsInRiver isInRiver(GpsRequest.Send request) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(request.getX(), request.getY()));

        // request의 city로 해당 도시의 하천 엔티티 리스트 조회
        List<River> rivers = riverRepository.findAllByCityName(request.getCity());
        // point가 하천들 중 내부에 있는지 검사
        boolean isInRiver = false;
        Long riverNo = null;
        for (River river : rivers) {
            if(river.getBoundary().contains(point)) {
                isInRiver = true;
                riverNo = river.getNo();
            }
        }

        return IsInRiver.builder()
                .isInRiver(isInRiver)
                .riverNo(riverNo)
                .build();
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class IsInRiver {
        private Boolean isInRiver;
        private Long riverNo;
    }
}
