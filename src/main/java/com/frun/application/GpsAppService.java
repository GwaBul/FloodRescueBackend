package com.frun.application;

import com.frun.api.request.GpsRequest;
import com.frun.api.response.GpsResponse;
import com.frun.domain.service.GpsService;
import com.frun.domain.service.RiverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GpsAppService {
    private final GpsService gpsService;
    private final RiverService riverService;

    public GpsResponse.Send processGps(GpsRequest.Send request) {
        // 사용자의 GPS가 특보 지역이 아니라면
        if(! request.getIsInCity()) {
            return GpsResponse.Send
                    .builder()
                    .isInRiver(false)
                    .build();
        }

        GpsService.IsInRiver inRiver = gpsService.isInRiver(request);
        // 사용자 GPS가 특보 지역의 하천들 내부가 아니라면
        if(! inRiver.getIsInRiver()) {
            return GpsResponse.Send
                    .builder()
                    .isInRiver(false)
                    .build();
        }

        // 탈출구 좌표 조회
        MultiPoint multiPoint = riverService.getExits(inRiver.getRiverNo());
        Point[] exits = riverService.convertMultiPointToPointArray(multiPoint);

        // 가까운 순서로 최대 3개 필터링
        GeometryFactory geometryFactory = new GeometryFactory();
        Point gps = geometryFactory.createPoint(new Coordinate(request.getX(), request.getY()));
        List<GpsResponse.PointDto> pointDtoList = Arrays.stream(exits)
                .sorted(Comparator.comparingDouble(exit -> exit.distance(gps)))
                .limit(3)
                .map(GpsResponse.PointDto::from)
                .toList();

        return GpsResponse.Send
                .builder()
                .isInRiver(true)
                .exits(pointDtoList)
                .build();
    }
}
