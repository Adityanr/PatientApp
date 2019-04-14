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

public class EditAge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_age);
        final SharedPreferences saveId=getSharedPreferences("Details",MODE_PRIVATE);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final EditText getage=findViewById(R.id.editag);
        Button conage=findViewById(R.id.confage);
        conage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fetchage=getage.getText().toString();
                String fetchid=saveId.getString("ID","Error");
                mRootRef.child("Patient details").child(fetchid).child("Age").setValue(fetchage);
                Intent sucact=new Intent(EditAge.this,SucupdateName.class);
                startActivity(sucact);
            }
        });
    }
}
