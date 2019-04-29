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

public class Editpassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final EditText newpassword=findViewById(R.id.newpass);
        final EditText confirmpassword=findViewById(R.id.confirmpass);
        final SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        saveScanId.getString("Scan_id","Error");
        Button okay=findViewById(R.id.ok);
        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newpas=newpassword.getText().toString();
                String confpas=confirmpassword.getText().toString();
                if(newpas.equals(confpas)) {
                    String patientid = saveScanId.getString("Scan_id","Error");
                    String patientpass = newpassword.getText().toString();
                    SharedPreferences saveRegId=getSharedPreferences("Details",MODE_PRIVATE);
                    SharedPreferences.Editor editRegSaveID=saveRegId.edit();
                    editRegSaveID.putString("ID", patientid);
                    editRegSaveID.apply();
                    mRootRef.child("Patient details").child(patientid).child("Password").setValue(patientpass);
                    Intent nextact3=new Intent(Editpassword.this,SucupdateName.class);
                    startActivity(nextact3);
                }
                else
                {
                    Toast.makeText(Editpassword.this,"Try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent goeditmen=new Intent(Editpassword.this,EditPatient.class);
        startActivity(goeditmen);
    }
}
