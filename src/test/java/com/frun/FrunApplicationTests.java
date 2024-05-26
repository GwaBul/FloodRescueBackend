package com.frun;

import com.frun.domain.interfaces.CityRepository;
import com.frun.domain.interfaces.RiverRepository;
import com.frun.domain.model.City;
import com.frun.domain.model.River;
import com.frun.domain.service.CityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.locationtech.jts.geom.Polygon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FrunApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(FrunApplicationTests.class);
    @Autowired
    private CityService cityService;
    @Autowired
    private RiverRepository riverRepository;
    @Autowired
    private CityRepository cityRepository;

    @Test
    void saveCity() {
        cityService.createCity("구미");
    }

    @Test
    void saveRiverName() {
        City city = cityRepository.findByName("부산");
        River river = River.builder()
                .name("온천천")
                .city(city)
                .build();
        riverRepository.save(river);
    }

    @Test
    void saveExitList() {
        Coordinate[] exitCoordinates = new Coordinate[] {
            new Coordinate(128.392010, 36.146077),
            new Coordinate(128.392696, 36.146207),
            new Coordinate(128.392918, 36.145930),
            new Coordinate(128.392209, 36.145608),
            new Coordinate(128.392705, 36.145788),
            new Coordinate(128.392764, 36.145580),
        };

        GeometryFactory geometryFactory = new GeometryFactory();
        MultiPoint exits = geometryFactory.createMultiPointFromCoords(exitCoordinates);
        exits.setSRID(4326);
        River foundRiver = riverRepository.findByName("디지털천");
        foundRiver.setExitList(exits);
        River save = riverRepository.save(foundRiver);

        Assertions.assertEquals(foundRiver.getName(), save.getName(), "saveExitList 테스트 실패");
    }

    @Test
    void saveRiverBoundary() {
        GeometryFactory factory = new GeometryFactory();
        Coordinate[] boundary = new Coordinate[] {
                new Coordinate(128.391857, 36.146110),
                new Coordinate(128.392087, 36.145304),
                new Coordinate(128.393109, 36.145522),
                new Coordinate(128.392883, 36.146315),
                new Coordinate(128.391857, 36.146110),
        };
        Polygon polygon = factory.createPolygon(boundary);
        polygon.setSRID(4326);
        River river = River.builder()
                .name("디지털천")
                .boundary(polygon)
                .build();

        River save = riverRepository.save(river);

        Assertions.assertEquals("디지털천", save.getName(), "saveRiverBoundary 테스트 실패");
    }

    @Test
    void findAllByCityName() {
        List<River> rivers = riverRepository.findAllByCityName("구미시");
        boolean match = rivers.stream()
                .anyMatch(river -> river.getName().equals("금오천"));

        // match가 true인지 테스트
        Assertions.assertTrue(match, "findAllByCityName 테스트 실패");
    }
}
