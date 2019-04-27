package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LatestPresc extends AppCompatActivity {

    ListView listViewpre4;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference mRootRef=database.getReference();

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Patientmeds pmed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_presc);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference mRootRef = database.getReference();
        SharedPreferences saveScanId = getSharedPreferences("Scan_Details", MODE_PRIVATE);
        String fetchid = saveScanId.getString("Scan_id", "Error");

        pmed=new Patientmeds();
        listViewpre4=findViewById(R.id.latestlist);
        list=new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.user_info,R.id.userinfo,list);


        mRootRef.child("Latest Presc").child(fetchid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    pmed = ds.getValue(Patientmeds.class);
                    list.add("\t\t\t\t\t\t\t\t" + pmed.getMed_name().toString() + "\n\n " + pmed.getDose().toString() + "\n\n" + "\t\t\t\t\t\t" + pmed.getDoc().toString() + "\n\n" + pmed.getDate().toString() + "\n\n" + pmed.getNod());


                }



                listViewpre4.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent gobackmen=new Intent(LatestPresc.this,MenuActivity.class);
        startActivity(gobackmen);
    }
}
