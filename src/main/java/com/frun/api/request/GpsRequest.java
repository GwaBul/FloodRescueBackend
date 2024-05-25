package com.frun.api.request;

import lombok.Getter;

public class GpsRequest {
    @Getter
    public static class Send {
        private Boolean isInCity;
        private String city;
        private Double x;
        private Double y;
    }
}
