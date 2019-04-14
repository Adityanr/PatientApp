package com.example.patientapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Testdate extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdate);
        findViewById(R.id.testbutt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText entryodate = findViewById(R.id.enterdate);
                Date c = Calendar.getInstance().getTime();


                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String createDate = entryodate.getText().toString();

                long dstamp=0;
                try {
                    Date d = (Date)df.parse(createDate);
                    dstamp=d.getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int finaldaycount=getTimeRemaining(dstamp);
                String finallyset=""+finaldaycount;
                TextView daycountset=findViewById(R.id.textView17);
                daycountset.setText(finallyset);

            }
        });

    }

    public int getTimeRemaining(long dateEvent)
    {
        Calendar sDate = toCalendar(dateEvent);
        Calendar eDate = toCalendar(System.currentTimeMillis());

        // Get the represented date in milliseconds
        long milis1 = sDate.getTimeInMillis();
        long milis2 = eDate.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = Math.abs(milis2 - milis1);

        return (int)(diff / (24 * 60 * 60 * 1000));
    }

    private Calendar toCalendar(long timestamp)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
