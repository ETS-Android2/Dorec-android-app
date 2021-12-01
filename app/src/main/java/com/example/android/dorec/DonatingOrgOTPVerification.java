package com.example.android.dorec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class DonatingOrgOTPVerification extends AppCompatActivity {

    FirebaseAuth institutionmAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks institutionmCallbacks;
    EditText institutionInputOTP1, institutionInputOTP2, institutionInputOTP3, institutionInputOTP4, institutionInputOTP5, institutionInputOTP6;
    ProgressBar institutionOtpVerificationProgressBar;
    String institutionSentOTP;
    TextView institutionSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donating_org_otpverification);

        institutionOtpVerificationProgressBar = (ProgressBar) findViewById(R.id.institutionOtpVerificationProgressBar);
        institutionSubmitBtn = (TextView) findViewById(R.id.institutionSubmitOTPBtn);
        institutionmAuth = FirebaseAuth.getInstance();

        institutionInputOTP1 = (EditText) findViewById(R.id.institutionInputOtp1);
        institutionInputOTP2 = (EditText) findViewById(R.id.institutionInputOtp2);
        institutionInputOTP3 = (EditText) findViewById(R.id.institutionInputOtp3);
        institutionInputOTP4 = (EditText) findViewById(R.id.institutionInputOtp4);
        institutionInputOTP5 = (EditText) findViewById(R.id.institutionInputOtp5);
        institutionInputOTP6 = (EditText) findViewById(R.id.institutionInputOtp6);

        TextView institutionPhoneNumber = (TextView) findViewById(R.id.institutionPhoneNumber);
        String institutionMobileNumber = getIntent().getStringExtra("institutionMobileNumber");
        institutionPhoneNumber.setText(String.format("+91-%s", institutionMobileNumber));

        institutionSentOTP = getIntent().getStringExtra("institutionOTPCode");

        institutionSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(institutionInputOTP1.getText().toString().trim().isEmpty() || institutionInputOTP2.getText().toString().trim().isEmpty() || institutionInputOTP3.getText().toString().trim().isEmpty() || institutionInputOTP4.getText().toString().trim().isEmpty() || institutionInputOTP5.getText().toString().trim().isEmpty() || institutionInputOTP6.getText().toString().trim().isEmpty()){
                    Toast.makeText(v.getContext(), "Enter all the digits of OTP", Toast.LENGTH_SHORT).show();
                }
                else{
                    String institutionEnteredOTP = institutionInputOTP1.getText().toString() +
                            institutionInputOTP2.getText().toString() +
                            institutionInputOTP3.getText().toString() +
                            institutionInputOTP4.getText().toString() +
                            institutionInputOTP5.getText().toString() +
                            institutionInputOTP6.getText().toString();

                    if(institutionSentOTP != null){
                        institutionOtpVerificationProgressBar.setVisibility(View.VISIBLE);
                        institutionSubmitBtn.setVisibility(View.GONE);

                        PhoneAuthCredential institutionCredential = PhoneAuthProvider.getCredential(institutionSentOTP, institutionEnteredOTP);
                        institutionmAuth.signInWithCredential(institutionCredential).addOnCompleteListener(DonatingOrgOTPVerification.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                institutionOtpVerificationProgressBar.setVisibility(View.GONE);
                                institutionSubmitBtn.setVisibility(View.VISIBLE);

                                if (task.isSuccessful()) {
                                    Intent i = new Intent(DonatingOrgOTPVerification.this, DonatePage.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    if(i.resolveActivity(getPackageManager()) != null){
                                        startActivity(i);
                                    }
                                } else {
                                    Toast.makeText(DonatingOrgOTPVerification.this, "Authentication Failed! Try Again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(DonatingOrgOTPVerification.this, "Error! Try Again", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        institutionNextOtp();
    }

    private void institutionNextOtp(){

        institutionInputOTP1 = (EditText) findViewById(R.id.institutionInputOtp1);
        institutionInputOTP2 = (EditText) findViewById(R.id.institutionInputOtp2);
        institutionInputOTP3 = (EditText) findViewById(R.id.institutionInputOtp3);
        institutionInputOTP4 = (EditText) findViewById(R.id.institutionInputOtp4);
        institutionInputOTP5 = (EditText) findViewById(R.id.institutionInputOtp5);
        institutionInputOTP6 = (EditText) findViewById(R.id.institutionInputOtp6);

        institutionInputOTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    institutionInputOTP2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        institutionInputOTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    institutionInputOTP3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        institutionInputOTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    institutionInputOTP4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        institutionInputOTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    institutionInputOTP5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        institutionInputOTP5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    institutionInputOTP6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}