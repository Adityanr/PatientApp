package com.example.patientapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.SecureRandom;

public class OTPActivity extends AppCompatActivity {
    private static final int MY_PERMISSION_REQUEST_SEND_SMS=0;
    Button sendit;
    EditText getphone;
    String strphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        sendit=findViewById(R.id.sendotp);
        getphone=findViewById(R.id.otpphone);
        sendit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSMessage();
            }
        });
    }

    protected void sendSMSMessage(){
        strphone=getphone.getText().toString();
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSION_REQUEST_SEND_SMS);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode,String permissions[],int[] grantResults){
        switch(requestCode){
            case MY_PERMISSION_REQUEST_SEND_SMS:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(strphone,null,"yoyo",null,null);
                    Toast.makeText(this, "OTP sent", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "OTP sent failed", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent gopass=new Intent(OTPActivity.this,MainActivity.class);
        startActivity(gopass);
    }
}
