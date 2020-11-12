package com.example.covid19appretrotest.activities;

import com.example.covid19appretrotest.views.ZoneActivity;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class zoneActivityTest {

    private ZoneActivity activity = new ZoneActivity();

    @Test
    public void notNull() throws Exception{
        assertNotNull(activity);
    }
}
