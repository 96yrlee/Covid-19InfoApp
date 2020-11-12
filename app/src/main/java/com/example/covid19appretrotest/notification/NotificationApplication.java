package com.example.covid19appretrotest.notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class NotificationApplication extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    private static CharSequence chName = "Covid19Notice";
    public static String description = "Reminder to check today's updated Covid-19 Info";
    private static int importance = NotificationManager.IMPORTANCE_LOW;

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationManager manager = (NotificationManager)getSystemService( NotificationManager.class );

        NotificationChannel mChannel = new NotificationChannel(
                CHANNEL_1_ID,
                chName,
                NotificationManager.IMPORTANCE_HIGH
        );
        mChannel.setDescription(description);

        manager.createNotificationChannel(mChannel);

        Log.d("notificationApp", "channel created");
    }
}

