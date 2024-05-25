package com.frun.domain.service;

import com.frun.domain.interfaces.RiverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiverService {
    private final RiverRepository riverRepository;

    public boolean isInRiver() {
        return true;
    }

    public Point[] convertMultiPointToPointArray(MultiPoint multiPoint) {
        if (multiPoint == null) {
            return new Point[0]; // MultiPoint가 null이면 빈 Point 배열 반환
        }

        int numPoints = multiPoint.getNumGeometries();
        Point[] points = new Point[numPoints];

        for (int i = 0; i < numPoints; i++) {
            points[i] = (Point) multiPoint.getGeometryN(i); // MultiPoint에서 Point 추출
        }

        return points;
    }

    public MultiPoint getExits(Long riverNo) {

    }
}
