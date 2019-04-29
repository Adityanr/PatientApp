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

public class Editallergy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editallergy);
        final SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final EditText editall=findViewById(R.id.editaller);
        Button conall=findViewById(R.id.confallergy);
        conall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fetchallergy=editall.getText().toString();
                String fetchid=saveScanId.getString("Scan_id","Error");
                mRootRef.child("Patient details").child(fetchid).child("Allergy").setValue(fetchallergy);
                Intent sucact=new Intent(Editallergy.this,SucupdateName.class);
                startActivity(sucact);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent editpnback=new Intent(Editallergy.this,EditPatient.class);
        startActivity(editpnback);
    }
}
