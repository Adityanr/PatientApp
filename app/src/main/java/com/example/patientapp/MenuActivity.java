package com.example.patientapp;

import android.content.Intent;
import android.app.Activity;
import instamojo.library.InstapayListener;
import instamojo.library.InstamojoPay;
import instamojo.library.Config;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    public static String fetchname;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();

        final String dayOfTheWeek = sdf.format(d);


        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        findViewById(R.id.paygatebutt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent payintent=new Intent(MenuActivity.this,Billactivity.class);
                startActivity(payintent);
            }
        });
        findViewById(R.id.prescbutt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent prescintent=new Intent(MenuActivity.this,LatestPresc.class);

                startActivity(prescintent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_profile:
                Intent selectpro=new Intent(MenuActivity.this,ProfileActivity.class);
                startActivity(selectpro);
                break;
            case R.id.nav_latestpresc:
                Intent selectlatpresc=new Intent(MenuActivity.this,RetrievePrescription.class);
                startActivity(selectlatpresc);
                break;
            case R.id.nav_signout:
                Intent selectsignout=new Intent(MenuActivity.this,StartingAct.class);
                startActivity(selectsignout);
                break;
            case R.id.nav_appoint:
                Intent selectappoint=new Intent(MenuActivity.this,AppointmentActivity.class);
                startActivity(selectappoint);
                break;

            case R.id.nav_doctor:
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                final DatabaseReference mRootRef=database.getReference();
                SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
                final String fetchid=saveScanId.getString("Scan_id","Error");
                mRootRef.child("Patient details").child(fetchid).child("Patient Name").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        fetchname=dataSnapshot.getValue().toString();
                        Intent selectlab=new Intent(MenuActivity.this,LabActivity.class);
                        startActivity(selectlab);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MenuActivity.this,"fail",Toast.LENGTH_SHORT).show();
                    }
                });
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            Toast.makeText(MenuActivity.this,"Press signout to exit",Toast.LENGTH_SHORT).show();
        }
        }

}
