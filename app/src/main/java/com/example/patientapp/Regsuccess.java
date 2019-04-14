package com.example.patientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Regsuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsuccess);
        Button sucset=findViewById(R.id.setlife);
        sucset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuact2=new Intent(Regsuccess.this, MenuActivity.class);
                startActivity(menuact2);
            }
        });
    }
}
