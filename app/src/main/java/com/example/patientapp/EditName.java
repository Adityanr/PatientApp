package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        final SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference mRootRef=database.getReference();
        final EditText fetchnewname=findViewById(R.id.editname);
        Button conf=findViewById(R.id.nextact);
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fetchid= saveScanId.getString("Scan_id","Error");
                String newy=fetchnewname.getText().toString();
                mRootRef.child("Patient details").child(fetchid).child("Patient Name").setValue(newy);
                Intent sucname=new Intent(EditName.this,SucupdateName.class);
                startActivity(sucname);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent editpnback=new Intent(EditName.this,EditPatient.class);
        startActivity(editpnback);
    }
}
