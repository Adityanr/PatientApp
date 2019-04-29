package com.example.patientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient);
        Button ename=findViewById(R.id.editname);
        Button eaddr=findViewById(R.id.editaddress);
        Button ephone=findViewById(R.id.editphone);
        Button eage=findViewById(R.id.editage);
        Button epn=findViewById(R.id.editpn);
        Button enok=findViewById(R.id.editnok);
        Button eallergy=findViewById(R.id.editallergy);

        ename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editnameact=new Intent(EditPatient.this,EditName.class);
                startActivity(editnameact);
            }
        });
        eaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editaddressact=new Intent(EditPatient.this,EditAddress.class);
                startActivity(editaddressact);
            }
        });
        ephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editphoneact=new Intent(EditPatient.this,Editphone.class);
                startActivity(editphoneact);
            }
        });
        eage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editageact=new Intent(EditPatient.this,EditAge.class);
                startActivity(editageact);
            }
        });


        eallergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editallergyact=new Intent(EditPatient.this,Editallergy.class);
                startActivity(editallergyact);
            }
        });
        epn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editpnact=new Intent(EditPatient.this,Editpn.class);
                startActivity(editpnact);
            }
        });
        enok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editnokact=new Intent(EditPatient.this,Editnok.class);
                startActivity(editnokact);
            }
        });
        findViewById(R.id.editthepass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editofpass=new Intent(EditPatient.this,Editpassword.class);
                startActivity(editofpass);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent pro2intent=new Intent(EditPatient.this,ProfileActivity2.class);
        startActivity(pro2intent);
    }
}
