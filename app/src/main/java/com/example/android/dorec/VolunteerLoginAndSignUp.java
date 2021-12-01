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

import java.util.concurrent.TimeUnit;


public class VolunteerLoginAndSignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_login_and_sign_up);

        mAuth = FirebaseAuth.getInstance();

        TextView loginBtn = (TextView) findViewById(R.id.logInBtn);
        TextView signUpBtn = (TextView) findViewById(R.id.signUpBtn);
        TextView loginNowBtn = (TextView) findViewById(R.id.loginNowBtn);
        TextView signUpNowBtn = (TextView) findViewById(R.id.signUpNowBtn);
        RelativeLayout loginVG = (RelativeLayout)findViewById(R.id.loginViewGroup);
        RelativeLayout signUpVG = (RelativeLayout)findViewById(R.id.signUpViewGroup);
        ProgressBar signUpProgressBar = (ProgressBar) findViewById(R.id.signUpProgressBar);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpBtn.setBackgroundResource(R.drawable.selected_background);
                signUpBtn.setTextColor(getResources().getColor(R.color.white));
                loginBtn.setBackgroundResource(R.drawable.profile_background);
                loginBtn.setTextColor(getResources().getColor(R.color.army));
                signUpVG.setVisibility(View.VISIBLE);
                loginVG.setVisibility(View.GONE);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpBtn.setBackgroundResource(R.drawable.profile_background);
                signUpBtn.setTextColor(getResources().getColor(R.color.army));
                loginBtn.setBackgroundResource(R.drawable.selected_background);
                loginBtn.setTextColor(getResources().getColor(R.color.white));
                signUpVG.setVisibility(View.GONE);
                loginVG.setVisibility(View.VISIBLE);
            }
        });

        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailID = (EditText) findViewById(R.id.emailInputID);
                EditText passID = (EditText) findViewById(R.id.passInputID);
                String email = emailID.getText().toString();
                String pass = passID.getText().toString();
                if(email.equals("yashmalaviya.mzp@gmail.com") && pass.equals("12345678")){
                    Intent i = new Intent(VolunteerLoginAndSignUp.this, VolunteerDonateOrReceive.class);
                    if(i.resolveActivity(getPackageManager()) != null){
                        startActivity(i);
                    }
                }
                else{
                    Toast.makeText(v.getContext(), "Wrong Credentials !", Toast.LENGTH_LONG).show();
                }
            }
        });

        signUpNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText contactNumber = (EditText) findViewById(R.id.contactNumberInputID);
                String number = contactNumber.getText().toString();

                if(number.trim().length() != 10){
                    Toast.makeText(v.getContext(), "Enter correct Contact number !", Toast.LENGTH_SHORT).show();
                }
                else {
                    signUpProgressBar.setVisibility(View.VISIBLE);
                    signUpNowBtn.setVisibility(View.GONE);

                    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            signUpProgressBar.setVisibility(View.GONE);
                            signUpNowBtn.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(VolunteerLoginAndSignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            signUpProgressBar.setVisibility(View.GONE);
                            signUpNowBtn.setVisibility(View.VISIBLE);
                            Intent i = new Intent(VolunteerLoginAndSignUp.this, OTPVerfication.class);
                            i.putExtra("mobileNumber", number);
                            i.putExtra("OTPCode", otp);
                            if(i.resolveActivity(getPackageManager()) != null){
                                startActivity(i);
                            }
                        }
                    };

                    PhoneAuthOptions options =
                            PhoneAuthOptions.newBuilder(mAuth)
                                    .setPhoneNumber("+91" + number)       // Phone number to verify
                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                    .setActivity(VolunteerLoginAndSignUp.this)                 // Activity (for callback binding)
                                    .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                    .build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
            }
        });
    }
    }