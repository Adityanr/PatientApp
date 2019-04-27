package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Billactivity extends AppCompatActivity {

    ListView listViewbill;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Billgen billgen;
    String getcash;
    String fetchname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billactivity);


        billgen=new Billgen();
        listViewbill=findViewById(R.id.billlist);
        list=new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.user_bill,R.id.mediname,list);


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        String fetchid=saveScanId.getString("Scan_id","Error");
        mRootRef.child("Patient details").child(fetchid).child("Patient Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchname=dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        findViewById(R.id.viewbillbutt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootRef.child("Patient Pharmacy").child(fetchname).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            billgen = ds.getValue(Billgen.class);
                            list.add(billgen.getMed_name() + "\t\t\t\t\t\t\t\t\t" + billgen.getPrice());
                        }
                        listViewbill.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                mRootRef.child("Patient Pharmacy").child(fetchname).child("total").child("med_name").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        getcash =dataSnapshot.getValue().toString();
                        TextView tottext=findViewById(R.id.totaltext);
                        tottext.setText(getcash);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        findViewById(R.id.gopaybutt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gopayintent=new Intent(Billactivity.this,PaymentGateway.class);
                gopayintent.putExtra("cash",getcash);
                startActivity(gopayintent);
            }
        });




    }

    @Override
    public void onBackPressed() {
        Intent menuactintent=new Intent(Billactivity.this,MenuActivity.class);
        startActivity(menuactintent);
    }
}
