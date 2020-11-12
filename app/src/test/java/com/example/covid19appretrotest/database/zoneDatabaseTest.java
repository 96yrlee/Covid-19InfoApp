package com.example.covid19appretrotest.database;

import com.example.covid19appretrotest.views.MainActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class zoneDatabaseTest {

    private Zone mZone;
    private ZoneDao dao;
    private CovidDatabase database;

    @Before
    public void setUp() throws Exception{
        mZone = zoneTestSetUp.getZone();

    }
    @Test
    public void notNull() throws Exception{
        assertNotNull(mZone);
    }
}
