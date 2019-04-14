package com.example.patientapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import java.util.Date;
import java.util.Random;

import static com.example.patientapp.MedReminderAct.m;
import static com.example.patientapp.MedReminderAct.l;

public class Notification_reciever3 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int p = l;
        NotificationManager notificationManager3= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            String ranndom;
            ranndom=random();
            String CHANNEL_ID = "my_id2";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            NotificationChannel notificationChannel3 = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel3.enableLights(true);
            notificationChannel3.setLightColor(Color.RED);
            notificationChannel3.enableVibration(true);
            notificationChannel3.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager3.createNotificationChannel(notificationChannel3);
        }


        Intent repeatintent=new Intent(context,Repeating_activity.class);
        repeatintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent3=PendingIntent.getActivity(context,p,repeatintent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder3=new NotificationCompat.Builder(context,"my_id2")
                .setContentIntent(pendingIntent3)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Medicine ALERT3")
                .setContentText("Click this to view the medicine333 to be consumed")
                .setAutoCancel(true);
        notificationManager3.notify(p,builder3.build());

    }
    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
}
