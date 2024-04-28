package com.frun.api.request;

import lombok.Getter;

public class GpsRequest {
    @Getter
    public static class Send {
        private double x;
        private double y;
    }
}
