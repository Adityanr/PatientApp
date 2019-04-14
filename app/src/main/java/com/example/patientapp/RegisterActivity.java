package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        saveScanId.getString("Scan_id","Error");
        final String pid=saveScanId.getString("Scan_id","Error");
        final EditText pname=findViewById(R.id.patname);
        final EditText paddr=findViewById(R.id.pataddress);
        final EditText pphone=findViewById(R.id.patphone);
        final EditText page=findViewById(R.id.patage);
        final EditText psex=findViewById(R.id.patgender);
        final EditText pnok=findViewById(R.id.patnok);
        Button next=findViewById(R.id.nextbutt);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextact=new Intent(RegisterActivity.this, RegisterActivity2.class);
                nextact.putExtra("patient_id",pid);
                nextact.putExtra("patient_name",pname.getText().toString());
                nextact.putExtra("patient_address",paddr.getText().toString());
                nextact.putExtra("patient_phone",pphone.getText().toString());
                nextact.putExtra("patient_age",page.getText().toString());
                nextact.putExtra("patient_sex",psex.getText().toString());
                nextact.putExtra("patient_nok",pnok.getText().toString());
                startActivity(nextact);
            }
        });
    }
}
