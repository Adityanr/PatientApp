package com.example.patientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SucupdateName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucupdate_name);
        Button gobck=findViewById(R.id.okay);
        gobck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tomenuact=new Intent(SucupdateName.this,MenuActivity.class);
                startActivity(tomenuact);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(SucupdateName.this,"Press set button to go to menu",Toast.LENGTH_SHORT).show();
    }
}
