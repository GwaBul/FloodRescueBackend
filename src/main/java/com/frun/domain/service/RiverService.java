package com.frun.domain.service;

import com.frun.domain.interfaces.RiverRepository;
import com.frun.domain.model.River;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Point;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiverService {
    private final RiverRepository riverRepository;

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

    @Transactional
    public MultiPoint getExits(Long riverNo) {
        Optional<River> optionalRiver = riverRepository.findById(riverNo);
        if(optionalRiver.isEmpty()) {
            log.error("존재하지 않는 하천입니다.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 하천입니다.");
        }

        return optionalRiver.get().getExitList();
    }
}
