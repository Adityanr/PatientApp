package com.example.patientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.juspay.godel.PaymentActivity;

public class StartingAct extends AppCompatActivity {
    public static int flagsign=0;
    public static int flagreg=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        findViewById(R.id.buttsign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagsign=1;
                Intent scanintent=new Intent(StartingAct.this, MenuActivity.class);
                startActivity(scanintent);
            }
        });
        findViewById(R.id.buttreg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagreg=1;
                Intent regintent=new Intent(StartingAct.this,QRscanner.class);
                startActivity(regintent);
            }
        });
    }
}
