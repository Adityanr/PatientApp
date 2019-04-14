package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        final EditText pass=findViewById(R.id.pwd);
        Button sign=findViewById(R.id.enter);
        Button reg=findViewById(R.id.register);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences saveId=getSharedPreferences("Details",MODE_PRIVATE);
                SharedPreferences.Editor editSaveID=saveId.edit();
                final String patient=saveScanId.getString("Scan_id","Error");
                editSaveID.putString("ID", patient);
                editSaveID.apply();
                final String passwrd=pass.getText().toString();
                mRootRef.child("Patient details").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(patient).exists())
                        {
                            String firepass=dataSnapshot.child(patient).child("Password").getValue().toString();
                        if(passwrd.equals(firepass))
                        {
                            Intent menuact=new Intent(MainActivity.this, MenuActivity.class);
                            startActivity(menuact);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Incorrect password",Toast.LENGTH_SHORT).show();
                        }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Username does not exists", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regact=new Intent(MainActivity.this, QRscanner.class);
                startActivity(regact);
            }
        });

    }
}
