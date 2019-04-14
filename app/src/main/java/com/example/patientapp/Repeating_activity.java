package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Repeating_activity extends AppCompatActivity {
    ListView listViewpre2;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference mRootRef=database.getReference();

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Patientmeds pmed;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeating_activity_layout);


            pmed=new Patientmeds();
            listViewpre2=findViewById(R.id.repeatlist);
            list=new ArrayList<>();
            adapter = new ArrayAdapter<String>(this,R.layout.user_info,R.id.userinfo,list);
            final SharedPreferences saveId=getSharedPreferences("Details",MODE_PRIVATE);
            String fetchid=saveId.getString("ID","Error");


            mRootRef.child("Prescription").child(fetchid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    int datingcount;
                    for(DataSnapshot ds:dataSnapshot.getChildren()) {
                        pmed = ds.getValue(Patientmeds.class);
                        datingcount = dateobtain(pmed.getDate());
                        int getdate=Integer.parseInt(String.valueOf(pmed.getNod()));
                        if ((datingcount !=getdate)&&(datingcount<getdate)) {
                            list.add(pmed.getMed_name().toString() + "\n\n " + pmed.getDose().toString() + "\n\n" + pmed.getDoc().toString() + "\n\n" + pmed.getDate().toString() + "\n\n" + pmed.getNod());

                        }
                    }
                    listViewpre2.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }
    public int dateobtain(final String givendate){

                Date c = Calendar.getInstance().getTime();


                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String createDate = givendate;

                long dstamp=0;
                try {
                    Date d = (Date)df.parse(createDate);
                    dstamp=d.getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int finaldaycount=getTimeRemaining(dstamp);
                return finaldaycount;
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
