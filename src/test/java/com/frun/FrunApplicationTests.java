package com.frun;

import com.frun.domain.interfaces.CityRepository;
import com.frun.domain.interfaces.RiverRepository;
import com.frun.domain.model.City;
import com.frun.domain.model.River;
import com.frun.domain.service.CityService;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Coordinate[] exits = new Coordinate[] {

        };

        GeometryFactory geometryFactory = new GeometryFactory();
        MultiPoint points = geometryFactory.createMultiPointFromCoords(exits);
        River foundRiver = riverRepository.findByName("온천천");
        foundRiver.setExitList(points);
        riverRepository.save(foundRiver);
    }
}
