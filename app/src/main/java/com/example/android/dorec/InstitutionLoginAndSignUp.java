package com.example.android.dorec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

public class InstitutionLoginAndSignUp extends AppCompatActivity {

    private FirebaseAuth institutionmAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks institutionmCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution_login_and_sign_up);

        TextView institutionSignUpNowBtn = (TextView) findViewById(R.id.institutionSignUpNowBtn);
        EditText institutionContactNumberInputID = (EditText) findViewById(R.id.institutionContactNumberInputID);
        ProgressBar institutionSignUpProgressBar = (ProgressBar) findViewById(R.id.institutionSignUpProgressBar);

        institutionmAuth = FirebaseAuth.getInstance();

        TextView institutionLoginBtn = (TextView) findViewById(R.id.institutionLogInBtn);
        TextView institutionSignUpBtn = (TextView) findViewById(R.id.institutionSignUpBtn);
        RelativeLayout institutionLoginVG = (RelativeLayout)findViewById(R.id.institutionLoginViewGroup);
        RelativeLayout institutionSignUpVG = (RelativeLayout)findViewById(R.id.institutionSignUpViewGroup);

        institutionSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                institutionSignUpBtn.setBackgroundResource(R.drawable.selected_background);
                institutionSignUpBtn.setTextColor(getResources().getColor(R.color.white));
                institutionLoginBtn.setBackgroundResource(R.drawable.profile_background);
                institutionLoginBtn.setTextColor(getResources().getColor(R.color.army));
                institutionSignUpVG.setVisibility(View.VISIBLE);
                institutionLoginVG.setVisibility(View.GONE);
            }
        });

        institutionLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                institutionSignUpBtn.setBackgroundResource(R.drawable.profile_background);
                institutionSignUpBtn.setTextColor(getResources().getColor(R.color.army));
                institutionLoginBtn.setBackgroundResource(R.drawable.selected_background);
                institutionLoginBtn.setTextColor(getResources().getColor(R.color.white));
                institutionSignUpVG.setVisibility(View.GONE);
                institutionLoginVG.setVisibility(View.VISIBLE);
            }
        });

        institutionSignUpNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String institutionNumber = institutionContactNumberInputID.getText().toString();

                if(institutionNumber.trim().length() != 10){
                    Toast.makeText(v.getContext(), "Enter correct Contact number !", Toast.LENGTH_SHORT).show();
                }
                else {
                    institutionSignUpProgressBar.setVisibility(View.VISIBLE);
                    institutionSignUpNowBtn.setVisibility(View.GONE);

                    institutionmCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            institutionSignUpProgressBar.setVisibility(View.GONE);
                            institutionSignUpNowBtn.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(InstitutionLoginAndSignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String institutionOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            institutionSignUpProgressBar.setVisibility(View.GONE);
                            institutionSignUpNowBtn.setVisibility(View.VISIBLE);
                            Intent i = new Intent(InstitutionLoginAndSignUp.this, DonatingOrgOTPVerification.class);
                            i.putExtra("institutionMobileNumber", institutionNumber);
                            i.putExtra("institutionOTPCode", institutionOtp);
                            if(i.resolveActivity(getPackageManager()) != null){
                                startActivity(i);
                            }
                        }
                    };

                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(institutionmAuth)
                                    .setPhoneNumber("+91" + institutionNumber)       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(InstitutionLoginAndSignUp.this)                 // Activity (for callback binding)
                                    .setCallbacks(institutionmCallbacks)          // OnVerificationStateChangedCallbacks
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
            }
        });
    }
}