package com.example.covid19appretrotest.notification;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import java.util.Calendar;

public class receiverTest {

    NotificationReceiver receiver = new NotificationReceiver();

    //check it can create itself
    @Test
    public void testConstruction(){
        assertNotNull( receiver );
    }
}
