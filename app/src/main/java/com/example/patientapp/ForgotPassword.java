package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final EditText newpassw=findViewById(R.id.newp);
        final EditText confirmpassw=findViewById(R.id.confp);
        SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        final String patientid=saveScanId.getString("Scan_id","Error");
        findViewById(R.id.setnewpass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newpassw.equals(confirmpassw)){
                    mRootRef.child("Patient details").child(patientid).child("Password").setValue(confirmpassw);
                    Intent successpass=new Intent(ForgotPassword.this,SucupdateName.class);
                    startActivity(successpass);
                }
                else{
                    Toast.makeText(ForgotPassword.this, "Entries do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
