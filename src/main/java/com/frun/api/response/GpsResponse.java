package com.frun.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

import java.util.List;

public class GpsResponse {
    @Builder
    @AllArgsConstructor
    @Getter
    public static class Send {
        private Boolean isInRiver;
        private List<PointDto> exits;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class PointDto {
        private Double x;
        private Double y;

        public static PointDto from(Point point) {
            return PointDto.builder()
                    .x(point.getX())
                    .y(point.getY())
                    .build();
        }
    }
}
