package com.example.patientapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/*public class MedReminderAct extends DialogFragment{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c= Calendar.getInstance();
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int minute=c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener)getActivity(),hour,minute, android.text.format.DateFormat.is24HourFormat(getActivity()));

    }
}*/

public class MedReminderAct extends AppCompatActivity{
    public static int m=(int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
    public static int k=(int) ((new Date().getTime() / 2000L) % Integer.MAX_VALUE);
    public static int l=(int) ((new Date().getTime() / 3000L) % Integer.MAX_VALUE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_reminder);

        Button setev=findViewById(R.id.setit);

        setev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar= Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,15);//setting time using edittext
                calendar.set(Calendar.MINUTE,32);
                calendar.set(Calendar.SECOND,40);
                Intent notintent=new Intent(getApplicationContext(),Notification_reciever.class);
                PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),m,notintent,PendingIntent.FLAG_ONE_SHOT);
                AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

                Calendar calendar2= Calendar.getInstance();
                calendar2.set(Calendar.HOUR_OF_DAY,15);//setting time using edittext
                calendar2.set(Calendar.MINUTE,32);
                calendar2.set(Calendar.SECOND,40);
                Intent notintent2=new Intent(getApplicationContext(),Notification_reciever2.class);
                PendingIntent pendingIntent2=PendingIntent.getBroadcast(getApplicationContext(),k,notintent2,PendingIntent.FLAG_ONE_SHOT);
                AlarmManager alarmManager2= (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager2.setRepeating(AlarmManager.RTC_WAKEUP,calendar2.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent2);

                Calendar calendar3= Calendar.getInstance();
                calendar3.set(Calendar.HOUR_OF_DAY,15);//setting time using edittext
                calendar3.set(Calendar.MINUTE,32);
                calendar3.set(Calendar.SECOND,40);
                Intent notintent3=new Intent(getApplicationContext(),Notification_reciever3.class);
                PendingIntent pendingIntent3=PendingIntent.getBroadcast(getApplicationContext(),l,notintent3,PendingIntent.FLAG_ONE_SHOT);
                AlarmManager alarmManager3= (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager3.setRepeating(AlarmManager.RTC_WAKEUP,calendar3.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent3);

            }
        });
    }
}
