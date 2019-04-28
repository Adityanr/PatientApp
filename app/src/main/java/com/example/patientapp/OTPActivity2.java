package com.example.patientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OTPActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp2);
        Intent fpass=getIntent();
        final EditText getotp=findViewById(R.id.otptype);
        final String fetchotp=fpass.getStringExtra("otp");
        findViewById(R.id.confotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((getotp.getText().toString()).equals(fetchotp))
                {
                    Intent newpassintent=new Intent(OTPActivity2.this,ForgotPassword.class);
                    startActivity(newpassintent);
                }
                else{
                    Toast.makeText(OTPActivity2.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent bckotp=new Intent(OTPActivity2.this,OTPActivity.class);
        startActivity(bckotp);
    }
}
