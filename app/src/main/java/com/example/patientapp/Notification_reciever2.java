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

import static com.example.patientapp.MedReminderAct.k;
import static com.example.patientapp.MedReminderAct.m;

public class Notification_reciever2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int n = k;
        NotificationManager notificationManager2= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_LOW;
            String ranndom;
            ranndom=random();
            String CHANNEL_ID = "my_id";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            NotificationChannel notificationChannel2 = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel2.enableLights(true);
            notificationChannel2.setLightColor(Color.RED);
            notificationChannel2.enableVibration(true);
            notificationChannel2.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager2.createNotificationChannel(notificationChannel2);
        }


        Intent repeatintent=new Intent(context,Repeating_activity.class);
        repeatintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent2=PendingIntent.getActivity(context,n,repeatintent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder2=new NotificationCompat.Builder(context,"my_id")
                .setContentIntent(pendingIntent2)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Medicine ALERT2")
                .setContentText("Click this to view the medicine222 to be consumed")
                .setAutoCancel(true);
        notificationManager2.notify(n,builder2.build());

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
