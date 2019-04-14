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

public class Editpn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpn);
        final SharedPreferences saveId=getSharedPreferences("Details",MODE_PRIVATE);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final EditText editphy=findViewById(R.id.newphy);
        Button conphy=findViewById(R.id.confphy);
        conphy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fetchphy=editphy.getText().toString();
                String fetchid=saveId.getString("ID","Error");
                mRootRef.child("Patient details").child(fetchid).child("Last Physician Name").setValue(fetchphy);
                Intent sucact=new Intent(Editpn.this,SucupdateName.class);
                startActivity(sucact);
            }
        });
    }
}
