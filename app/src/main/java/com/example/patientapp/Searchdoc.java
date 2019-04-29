package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Searchdoc extends AppCompatActivity {
    ListView listViewpre3;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference mRootRef=database.getReference();

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Patientmeds pmed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdoc);
        Intent searchintent=getIntent();
        final String strsearch = searchintent.getStringExtra("search");
        pmed=new Patientmeds();
        listViewpre3=findViewById(R.id.searchlist);
        list=new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.user_info,R.id.userinfo,list);

        final SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        String fetchid=saveScanId.getString("Scan_id","Error");

        mRootRef.child("Prescription").child(fetchid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    pmed = ds.getValue(Patientmeds.class);
                    String fetchdoc=pmed.getDoc().toString();
                    String fetchdate=pmed.getDate().toString();
                    String fetchmed=pmed.getMed_name().toString();
                    if(strsearch.equals(fetchdoc)) {

                        list.add("\t\t\t\t\t\t\t\t" + pmed.getMed_name().toString() + "\n\n " + pmed.getDose().toString() + "\n\n" + "\t\t\t\t\t\t" + pmed.getDoc().toString() + "\n\n" + pmed.getDate().toString() + "\n\n" + pmed.getNod());
                    }
                    else if(strsearch.equals(fetchdate)){
                        list.add("\t\t\t\t\t\t\t\t" + pmed.getMed_name().toString() + "\n\n " + pmed.getDose().toString() + "\n\n" + "\t\t\t\t\t\t" + pmed.getDoc().toString() + "\n\n" + pmed.getDate().toString() + "\n\n" + pmed.getNod());
                }
                    else if(strsearch.equals(fetchmed)){
                        list.add("\t\t\t\t\t\t\t\t" + pmed.getMed_name().toString() + "\n\n " + pmed.getDose().toString() + "\n\n" + "\t\t\t\t\t\t" + pmed.getDoc().toString() + "\n\n" + pmed.getDate().toString() + "\n\n" + pmed.getNod());
                    }
                }



                listViewpre3.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listViewpre3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String data=(String)listViewpre3.getItemAtPosition(position);
                StringTokenizer tokens = new StringTokenizer(data, "\n\n");
                String schmed=tokens.nextToken();
                String schdose=tokens.nextToken();
                String schdoc=tokens.nextToken();
                String schdate=tokens.nextToken();
                String schnod=tokens.nextToken();
                Intent schact=new Intent(Searchdoc.this,MedReminderAct.class);
                schact.putExtra("med_name",schmed);
                schact.putExtra("dosage",schdose);
                schact.putExtra("docname",schdoc);
                schact.putExtra("date",schdate);
                schact.putExtra("nod",schnod);
                startActivity(schact);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent retsearch=new Intent(Searchdoc.this,RetrievePrescription.class);
        startActivity(retsearch);
    }
}

