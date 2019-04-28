package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.patientapp.MenuActivity.fetchname;

public class LabActivity extends AppCompatActivity {
    ListView listViewlab;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference mRootRef=database.getReference();

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Labinit labob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
        labob=new Labinit();
        listViewlab=findViewById(R.id.lablist);
        list=new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.lab_info,R.id.textlab,list);
        final SharedPreferences saveId=getSharedPreferences("Details",MODE_PRIVATE);
        String fetchid=saveId.getString("ID","Error");
        mRootRef.child("Lab Results").child(fetchname).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    labob=ds.getValue(Labinit.class);
                    list.add(labob.getType()+"\n\n"+labob.getResult()+"\n\n\n\n\n\n\n\n\n"+labob.getDate());
                }
                listViewlab.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent labtomenu=new Intent(LabActivity.this,MenuActivity.class);
        startActivity(labtomenu);
    }
}
