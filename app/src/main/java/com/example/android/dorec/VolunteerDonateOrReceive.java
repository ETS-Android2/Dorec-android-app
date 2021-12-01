package com.example.android.dorec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class VolunteerDonateOrReceive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_donate_or_receive);

        whatToDo();
    }

    private void whatToDo(){
        TextView receive = (TextView)findViewById(R.id.ReceiveBtn);
        TextView donate = (TextView) findViewById(R.id.DonateBtn);

        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VolunteerDonateOrReceive.this, ReceivingActivity.class);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VolunteerDonateOrReceive.this, DonatePage.class);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });
    }
}