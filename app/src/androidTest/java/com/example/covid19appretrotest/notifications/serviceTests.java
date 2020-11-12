package com.example.covid19appretrotest.notifications;


import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ServiceTestRule;

import com.example.covid19appretrotest.notification.NotificationService;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class serviceTests {

    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Test
    public void testServiceStarts() {
        Intent serviceIntent = NotificationService.createNotificationServiceIntent( getApplicationContext() );



    }
}
