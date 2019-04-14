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

public class EditAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        final SharedPreferences saveId=getSharedPreferences("Details",MODE_PRIVATE);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final EditText newaddress=findViewById(R.id.editaddr);
        Button con=findViewById(R.id.confaddr);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fetchid=saveId.getString("ID","Error");
                String fetchaddr=newaddress.getText().toString();
                mRootRef.child("Patient details").child(fetchid).child("Address").setValue(fetchaddr);
                Intent sucact=new Intent(EditAddress.this,SucupdateName.class);
                startActivity(sucact);
            }
        });
    }
}
