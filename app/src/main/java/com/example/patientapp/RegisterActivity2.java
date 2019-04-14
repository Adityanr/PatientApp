package com.example.patientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        final EditText bg=findViewById(R.id.bloodgroup);
        final EditText phyn=findViewById(R.id.phyname);
        final EditText aller=findViewById(R.id.allergy);
        Button go=findViewById(R.id.nxt);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextact=getIntent();
                String strid = nextact.getStringExtra("patient_id");
                String strname = nextact.getStringExtra("patient_name");
                String straddress = nextact.getStringExtra("patient_address");
                String strphone = nextact.getStringExtra("patient_phone");
                String strage = nextact.getStringExtra("patient_age");
                String strsex = nextact.getStringExtra("patient_sex");
                String strnok = nextact.getStringExtra("patient_nok");
                Intent nextact2=new Intent(RegisterActivity2.this,RegisterActivity3.class);
                nextact2.putExtra("Blood_group",bg.getText().toString());
                nextact2.putExtra("Physician_Name",phyn.getText().toString());
                nextact2.putExtra("Allergy",aller.getText().toString());
                nextact2.putExtra("ID",strid);
                nextact2.putExtra("Name",strname);
                nextact2.putExtra("Address",straddress);
                nextact2.putExtra("Phone",strphone);
                nextact2.putExtra("Age",strage);
                nextact2.putExtra("Sex",strsex);
                nextact2.putExtra("Nok",strnok);
                startActivity(nextact2);
            }
        });
    }
}
