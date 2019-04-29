package com.example.patientapp;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import instamojo.library.InstamojoPay;
import instamojo.library.InstapayListener;

public class PaymentGateway extends AppCompatActivity {
    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        final Activity activity = this;
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initListener();
        instamojoPay.start(activity, pay, listener);
    }

    InstapayListener listener;


    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG)
                        .show();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        Intent gopayintent=getIntent();
        final EditText payeditemail=findViewById(R.id.payemail);
        final EditText payeditphone=findViewById(R.id.payphone);
        final TextView payeditamount=findViewById(R.id.payamount);
        payeditamount.setText(gopayintent.getStringExtra("cash"));
        final TextView payeditpurpose=findViewById(R.id.paypurpose);
        final EditText payeditbuyername=findViewById(R.id.paybuyername);
        findViewById(R.id.paybutt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fetchemail=payeditemail.getText().toString().trim();
                final String fetchphone=payeditphone.getText().toString().trim();
                final String fetchamount=payeditamount.getText().toString();
                final String fetchpurpose=payeditpurpose.getText().toString();
                final String fetchbuyername=payeditbuyername.getText().toString();
                callInstamojoPay(fetchemail,fetchphone,fetchamount,fetchpurpose,fetchbuyername);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent billintent=new Intent(PaymentGateway.this,Billactivity.class);
        startActivity(billintent);
    }
}
