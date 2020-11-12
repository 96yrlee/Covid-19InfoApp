package com.example.covid19appretrotest.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.covid19appretrotest.views.MainActivity;
import com.example.covid19appretrotest.R;

public class NotificationService extends JobIntentService {

    public static final String CHANNEL_1_ID = NotificationApplication.CHANNEL_1_ID; //=channel1
    public static final String NOTIF_BUNDLE_KEY = "bundleData";
    public static final String ALARM_ACTION_KEY = "alarmActionStart";

    private static final String notifTitle = "Covid-19 Info Update";

    public static String message = NotificationApplication.description; //="Reminder to check today's updated Covid-19 Info"

    private NotificationManagerCompat notificationManager;
    private static final int NOTIFICATION_ID = 1234321;

    public NotificationService() {
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Log.d("notificationService", "onHandleWork");
        startNotification();
    }


    private void startNotification() {
        Context appContext = getApplicationContext();

        notificationManager = NotificationManagerCompat.from( appContext );

        Intent openIntent = new Intent(appContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                appContext,
                NOTIFICATION_ID,
                openIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        Notification notification = new NotificationCompat.Builder(appContext, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle(notifTitle)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction( R.mipmap.ic_launcher, "open app", pendingIntent)
                .build();

        if(notification != null){
            notificationManager.notify(NOTIFICATION_ID, notification);
        }else{
            Toast.makeText(this, "its null", Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent createNotificationServiceIntent(Context context) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ALARM_ACTION_KEY);

        Log.d("notificationService", "intent created");

        return intent;
    }

    static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, NotificationService.class, 123, intent);
    }

}

