package com.example.covid19appretrotest.notification;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class serviceTest {

    NotificationService service = new NotificationService();

    //check it can create itself
    @Test
    public void testConstruction(){
        assertNotNull( service );
    }
}
