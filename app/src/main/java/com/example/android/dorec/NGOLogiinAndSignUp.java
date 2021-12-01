package com.example.android.dorec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NGOLogiinAndSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngologiin_and_sign_up);

        whatToDo();
    }

    private void whatToDo(){
        TextView ngoLoginBtn = (TextView) findViewById(R.id.ngoLogInBtn);
        TextView ngoSignUpBtn = (TextView) findViewById(R.id.ngoSignUpBtn);
        TextView ngoSignUpNowBtn = (TextView) findViewById(R.id.ngoSignUpNowBtn);
        RelativeLayout ngoLoginVG = (RelativeLayout)findViewById(R.id.ngoLoginViewGroup);
        RelativeLayout ngoSignUpVG = (RelativeLayout)findViewById(R.id.ngoSignUpViewGroup);

        ngoSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ngoSignUpBtn.setBackgroundResource(R.drawable.selected_background);
                ngoSignUpBtn.setTextColor(getResources().getColor(R.color.white));
                ngoLoginBtn.setBackgroundResource(R.drawable.profile_background);
                ngoLoginBtn.setTextColor(getResources().getColor(R.color.army));
                ngoSignUpVG.setVisibility(View.VISIBLE);
                ngoLoginVG.setVisibility(View.GONE);
            }
        });

        ngoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ngoSignUpBtn.setBackgroundResource(R.drawable.profile_background);
                ngoSignUpBtn.setTextColor(getResources().getColor(R.color.army));
                ngoLoginBtn.setBackgroundResource(R.drawable.selected_background);
                ngoLoginBtn.setTextColor(getResources().getColor(R.color.white));
                ngoSignUpVG.setVisibility(View.GONE);
                ngoLoginVG.setVisibility(View.VISIBLE);
            }
        });

        ngoSignUpNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NGOLogiinAndSignUp.this, ReceivingActivity.class);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivity(i);
                }
            }
        });
    }
}