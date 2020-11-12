package com.example.covid19appretrotest.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.covid19appretrotest.views.MainActivity;

import java.util.Calendar;

public class NotificationReceiver extends BroadcastReceiver {

    static final String TAG = "NotificationReceiver";
    private static final int PENDING_RECEIVER_ID = 1234;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = NotificationService.createNotificationServiceIntent(context);
        NotificationService.enqueueWork(context, serviceIntent );
    }

    /**
     * Will create the ic_alarm which will then call upon this receiver
     * @param context
     * @param
     */
    public static void startNotificationAlarm(Context context, Calendar c/*, long time*/){
        Log.d("TAG", "startNotificationAlarm is called");

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, PENDING_RECEIVER_ID, intent, 0);

        //makes sure it fires the coming time, and not immediately if current past set time
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        Toast.makeText(context, "Notification Alarm Started", Toast.LENGTH_LONG ).show();

        //for testing, use this
        //alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    public static void cancelAlarm(Context context){
        Log.d("TAG", "cancelAlarm is called");

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        Intent intent = new Intent(context, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, PENDING_RECEIVER_ID, intent, 0);

        alarmManager.cancel(pendingIntent);
    }


    public static Calendar setAlarmTime(){
        // hardcode for now, implement timepicker later
        int hourOfDay = 12;
        int minute = 30;

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        return c;
    }
}

