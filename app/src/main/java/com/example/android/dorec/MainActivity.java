package com.example.android.dorec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToActivities();
    }

    private void goToActivities(){
        RelativeLayout volunteerViewGroup = (RelativeLayout) findViewById(R.id.volunteersViewGroupID);
        RelativeLayout institutionViewGroup = (RelativeLayout)findViewById(R.id.institutionsViewGroupID);
        RelativeLayout ngoViewGroup = (RelativeLayout)findViewById(R.id.ngoViewGroupID);

        volunteerViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VolunteerLoginAndSignUp.class);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });

        institutionViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InstitutionLoginAndSignUp.class);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });

        ngoViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NGOLogiinAndSignUp.class);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });
    }
}