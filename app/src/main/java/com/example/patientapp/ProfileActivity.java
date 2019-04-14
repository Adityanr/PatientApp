package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();

        final TextView getname=findViewById(R.id.Viewname);
        final TextView getage=findViewById(R.id.Viewage);
        final TextView getgender=findViewById(R.id.Viewgender);
        final TextView getaddr=findViewById(R.id.Viewaddress);
        final TextView getphone=findViewById(R.id.Viewphone);
        final TextView getnok=findViewById(R.id.Viewnok);
        final Button nexpro=findViewById(R.id.cont);

                SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
                String fetchid=saveScanId.getString("Scan_id","Error");
                mRootRef.child("Patient details").child(fetchid).child("Patient Name").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String fetchname=dataSnapshot.getValue().toString();
                        getname.setText(fetchname);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mRootRef.child("Patient details").child(fetchid).child("Age").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String fetchage=dataSnapshot.getValue().toString();
                        getage.setText(fetchage);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mRootRef.child("Patient details").child(fetchid).child("Address").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String fetchaddress=dataSnapshot.getValue().toString();
                        getaddr.setText(fetchaddress);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mRootRef.child("Patient details").child(fetchid).child("Phone").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String fetchphone=dataSnapshot.getValue().toString();
                        getphone.setText(fetchphone);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mRootRef.child("Patient details").child(fetchid).child("Gender").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String fetchgender=dataSnapshot.getValue().toString();
                        getgender.setText(fetchgender);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mRootRef.child("Patient details").child(fetchid).child("Next of Kin").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String fetchnok=dataSnapshot.getValue().toString();
                        getnok.setText(fetchnok);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




        nexpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextprofile=new Intent(ProfileActivity.this,ProfileActivity2.class);
                startActivity(nextprofile);

            }
        });


    }
}
