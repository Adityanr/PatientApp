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

public class Editnok extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnok);
        final SharedPreferences saveId=getSharedPreferences("Details",MODE_PRIVATE);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final EditText editno=findViewById(R.id.unok);
        Button connok=findViewById(R.id.confnok);
        connok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fetchnok=editno.getText().toString();
                String fetchid=saveId.getString("ID","Error");
                mRootRef.child("Patient details").child(fetchid).child("Next of Kin").setValue(fetchnok);
                Intent sucact=new Intent(Editnok.this, SucupdateName.class);
                startActivity(sucact);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent editpnback=new Intent(Editnok.this,EditPatient.class);
        startActivity(editpnback);
    }
}
