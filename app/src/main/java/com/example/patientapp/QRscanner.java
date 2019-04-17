package com.example.patientapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.patientapp.StartingAct.flagreg;
import static com.example.patientapp.StartingAct.flagsign;

public class QRscanner extends AppCompatActivity implements View.OnClickListener {
    private Button buttonscan;
    private EditText viewid;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);
        buttonscan = (Button) findViewById(R.id.scanbutt);
        viewid =  findViewById(R.id.textid);
        buttonscan.setOnClickListener(this);
        qrScan = new IntentIntegrator(this);
        findViewById(R.id.proceedbutt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flagsign==1)
                {
                    flagsign=0;
                    SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
                    SharedPreferences.Editor editSaveScanID=saveScanId.edit();
                    editSaveScanID.putString("Scan_id",viewid.getText().toString());
                    editSaveScanID.apply();
                    Intent nextsignintent=new Intent(QRscanner.this,MainActivity.class);
                    startActivity(nextsignintent);
                }
                else if (flagreg==1)
                {
                    flagreg=0;
                    Intent nextregintent=new Intent(QRscanner.this,RegisterActivity.class);
                    startActivity(nextregintent);
                }
            }
        });
        findViewById(R.id.backstart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backstartintent=new Intent(QRscanner.this,StartingAct.class);
                startActivity(backstartintent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();

            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews

                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    viewid.setText(result.getContents());
                    SharedPreferences saveScanId=getSharedPreferences("Scan_Details",MODE_PRIVATE);
                    SharedPreferences.Editor editSaveScanID=saveScanId.edit();
                    editSaveScanID.putString("Scan_id",result.getContents());
                    editSaveScanID.apply();

                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        qrScan.initiateScan();
    }
}
