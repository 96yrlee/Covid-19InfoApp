package com.example.covid19appretrotest.activities;

import com.example.covid19appretrotest.views.MainActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() throws Exception{
        mainActivity = new MainActivity();
    }
    @Test
    public void notNull() throws Exception{
        assertNotNull(mainActivity);
    }

    @Test
    public void testGetDate() throws Exception{
        String date = mainActivity.getDateText();
        assertTrue(date.length() == 10); // format is YYYY-MM-DD
    }

}
