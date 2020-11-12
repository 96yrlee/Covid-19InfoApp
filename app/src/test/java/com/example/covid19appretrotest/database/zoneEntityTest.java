package com.example.covid19appretrotest.database;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class zoneEntityTest {

    private Zone zone;

    @Before
    public void setUp(){
        zone = zoneTestSetUp.getZone();
    }

    @Test
    public void notNull() throws Exception{
        assertNotNull(zone);
    }

    @Test
    public void getAndSetTest() throws Exception{

    }



}
