package com.example.covid19appretrotest.database;

import org.junit.Before;

public class zoneTestSetUp {
    private static Zone zone;

    @Before
    public void setUp(){
        zone = new Zone("Nov 1st 2020", "Global", 10000000L,
                7342359, 414124, 3619774, 3308461,
                31527, 21016, 1152,
                1591782903866L, 7753933875L, 102586329L);
    }

    public static Zone getZone() {
        return zone;
    }

    public static void setZone(Zone zone) {
        zoneTestSetUp.zone = zone;
    }
}
