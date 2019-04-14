package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final EditText newpassword=findViewById(R.id.newpass);
        final EditText confirmpassword=findViewById(R.id.confirmpass);
        Button okay=findViewById(R.id.ok);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpas=newpassword.getText().toString();
                String confpas=confirmpassword.getText().toString();
                if(newpas.equals(confpas)) {
                    Intent nextact2 = getIntent();
                    String patientname = nextact2.getStringExtra("Name");
                    String patientid = nextact2.getStringExtra("ID");
                    String patientaddress = nextact2.getStringExtra("Address");
                    String patientphone = nextact2.getStringExtra("Phone");
                    String patientage = nextact2.getStringExtra("Age");
                    String patientsex = nextact2.getStringExtra("Sex");
                    String patientnok = nextact2.getStringExtra("Nok");
                    String patientbg = nextact2.getStringExtra("Blood_group");
                    String patientallergy = nextact2.getStringExtra("Allergy");
                    String patientphy = nextact2.getStringExtra("Physician_Name");
                    String patientpass = newpassword.getText().toString();
                    SharedPreferences saveRegId=getSharedPreferences("Details",MODE_PRIVATE);
                    SharedPreferences.Editor editRegSaveID=saveRegId.edit();
                    editRegSaveID.putString("ID", patientid);
                    editRegSaveID.apply();
                    mRootRef.child("Patient details").child(patientid).child("Patient Name").setValue(patientname);
                    mRootRef.child("Patient details").child(patientid).child("Address").setValue(patientaddress);
                    mRootRef.child("Patient details").child(patientid).child("Phone").setValue(patientphone);
                    mRootRef.child("Patient details").child(patientid).child("Age").setValue(patientage);
                    mRootRef.child("Patient details").child(patientid).child("Gender").setValue(patientsex);
                    mRootRef.child("Patient details").child(patientid).child("Next of Kin").setValue(patientnok);
                    mRootRef.child("Patient details").child(patientid).child("Blood Group").setValue(patientbg);
                    mRootRef.child("Patient details").child(patientid).child("Allergy").setValue(patientallergy);
                    mRootRef.child("Patient details").child(patientid).child("Last Physician Name").setValue(patientphy);
                    mRootRef.child("Patient details").child(patientid).child("Password").setValue(patientpass);
                    Intent nextact3=new Intent(RegisterActivity3.this,Regsuccess.class);
                    startActivity(nextact3);
                }
                else
                {
                    Toast.makeText(RegisterActivity3.this,"Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
