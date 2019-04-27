package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference mRootRef=database.getReference();
        final SharedPreferences saveId=getSharedPreferences("Details",MODE_PRIVATE);
        String fetchid=saveId.getString("ID","Error");
        final TextView getbg=findViewById(R.id.Viewbg);
        final TextView getallergy=findViewById(R.id.Viewallergy);
        final TextView getphyname=findViewById(R.id.Viewphyname);
        Button ed=findViewById(R.id.editdetails);
        Button backmenu=findViewById(R.id.btm);
        mRootRef.child("Patient details").child(fetchid).child("Blood Group").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getbg.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mRootRef.child("Patient details").child(fetchid).child("Allergy").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getallergy.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mRootRef.child("Patient details").child(fetchid).child("Last Physician Name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getphyname.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editact=new Intent(ProfileActivity2.this,EditPatient.class);
                startActivity(editact);
            }
        });
        backmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btmact=new Intent(ProfileActivity2.this,MenuActivity.class);
                startActivity(btmact);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent prointentact=new Intent(ProfileActivity2.this,ProfileActivity.class);
        startActivity(prointentact);
    }
}
