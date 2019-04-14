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

public class Editphone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editphone);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final SharedPreferences saveId=getSharedPreferences("Details",MODE_PRIVATE);
        final EditText newphone=findViewById(R.id.editph);
        Button conph=findViewById(R.id.confphone);
        conph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fetchphone=newphone.getText().toString();
                String fetchid=saveId.getString("ID","Error");
                mRootRef.child("Patient details").child(fetchid).child("Phone").setValue(fetchphone);
                Intent sucact=new Intent(Editphone.this,SucupdateName.class);
                startActivity(sucact);
            }
        });
    }
}
