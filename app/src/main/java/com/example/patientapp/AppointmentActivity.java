package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AppointmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        final String fetchid=saveScanId.getString("Scan_id","Error");

        final TextView textappoint=findViewById(R.id.appointtext);
        findViewById(R.id.linkgen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textappoint.setText("https://too-too-trouble.000webhostapp.com/patientappointdept.html?id="+fetchid);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent apptomen=new Intent(AppointmentActivity.this,MenuActivity.class);
        startActivity(apptomen);
    }
}
