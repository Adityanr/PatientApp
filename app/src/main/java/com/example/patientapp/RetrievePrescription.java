package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class RetrievePrescription extends AppCompatActivity {

    ListView listViewpre;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference mRootRef=database.getReference();

    ArrayList<String> list;
    ArrayAdapter <String> adapter;
    Patientmeds pmed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_prescription);
        final SearchView searchthedoc=findViewById(R.id.searchdoctext);
        final CharSequence query = searchthedoc.getQuery();
        Button searchbutt=findViewById(R.id.searchdoc);
        pmed=new Patientmeds();
        listViewpre=findViewById(R.id.listpre);
         list=new ArrayList<>();
         adapter = new ArrayAdapter<String>(this,R.layout.user_info,R.id.userinfo,list);
        final SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
        String fetchid=saveScanId.getString("Scan_id","Error");

        mRootRef.child("Prescription").child(fetchid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                        pmed = ds.getValue(Patientmeds.class);
                        list.add("\t\t\t\t\t\t\t\t" + pmed.getMed_name().toString() + "\n\n " + pmed.getDose().toString() + "\n\n" + "\t\t\t\t\t\t" + pmed.getDoc().toString() + "\n\n" + "\t\t\t"+pmed.getDate().toString() + "\n\n" + "\t\t\t\t"+pmed.getNod());
                }
                listViewpre.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        searchbutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchintent=new Intent(RetrievePrescription.this,Searchdoc.class);
                searchintent.putExtra("search",query.toString());
                startActivity(searchintent);
            }
        });


        listViewpre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String data=(String)listViewpre.getItemAtPosition(position);
                StringTokenizer tokens = new StringTokenizer(data, "\n\n");
                String schmed=tokens.nextToken();
                String schdose=tokens.nextToken();
                String schdoc=tokens.nextToken();
                String schdate=tokens.nextToken();
                String schnod=tokens.nextToken();
                Intent schact=new Intent(RetrievePrescription.this,MedReminderAct.class);
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
        Intent gotomenu=new Intent(RetrievePrescription.this,MenuActivity.class);
        startActivity(gotomenu);
    }
}
