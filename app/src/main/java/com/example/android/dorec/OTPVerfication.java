package com.example.android.dorec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPVerfication extends AppCompatActivity {

    EditText inputOTP1, inputOTP2, inputOTP3, inputOTP4, inputOTP5, inputOTP6;
    String sentOTP;
    ProgressBar otpVerificationProgressBar;
    TextView submitBtn;
    FirebaseAuth mAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverfication);

        otpVerificationProgressBar = (ProgressBar) findViewById(R.id.otpVerificationProgressBar);
        submitBtn = (TextView) findViewById(R.id.submitOTPBtn);
        mAuth = FirebaseAuth.getInstance();

        inputOTP1 = (EditText) findViewById(R.id.inputOtp1);
        inputOTP2 = (EditText) findViewById(R.id.inputOtp2);
        inputOTP3 = (EditText) findViewById(R.id.inputOtp3);
        inputOTP4 = (EditText) findViewById(R.id.inputOtp4);
        inputOTP5 = (EditText) findViewById(R.id.inputOtp5);
        inputOTP6 = (EditText) findViewById(R.id.inputOtp6);

        TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        String mobileNumber = getIntent().getStringExtra("mobileNumber");
        phoneNumber.setText(String.format("+91-%s", mobileNumber));

        sentOTP = getIntent().getStringExtra("OTPCode");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputOTP1.getText().toString().trim().isEmpty() || inputOTP2.getText().toString().trim().isEmpty() || inputOTP3.getText().toString().trim().isEmpty() || inputOTP4.getText().toString().trim().isEmpty() || inputOTP5.getText().toString().trim().isEmpty() || inputOTP6.getText().toString().trim().isEmpty()){
                    Toast.makeText(v.getContext(), "Enter all the digits of OTP", Toast.LENGTH_SHORT).show();
                }
                else{
                    String enteredOTP = inputOTP1.getText().toString() +
                            inputOTP2.getText().toString() +
                            inputOTP3.getText().toString() +
                            inputOTP4.getText().toString() +
                            inputOTP5.getText().toString() +
                            inputOTP6.getText().toString();

                    if(sentOTP != null){
                        otpVerificationProgressBar.setVisibility(View.VISIBLE);
                        submitBtn.setVisibility(View.GONE);

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sentOTP, enteredOTP);
                        mAuth.signInWithCredential(credential).addOnCompleteListener(OTPVerfication.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                otpVerificationProgressBar.setVisibility(View.GONE);
                                submitBtn.setVisibility(View.VISIBLE);

                                if (task.isSuccessful()) {
                                    Intent i = new Intent(OTPVerfication.this, VolunteerDonateOrReceive.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    if(i.resolveActivity(getPackageManager()) != null){
                                        startActivity(i);
                                    }
                                } else {
                                    Toast.makeText(OTPVerfication.this, "Authentication Failed! Try Again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(OTPVerfication.this, "Error! Try Again", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        nextOtp();

        /*findViewById(R.id.resentOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber("+91" + mobileNumber)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(OTPVerfication.this)                 // Activity (for callback binding)
                                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
                mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(VolunteerLoginAndSignUp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                        Intent i = new Intent(VolunteerLoginAndSignUp.this, OTPVerfication.class);
                        i.putExtra("mobileNumber", number);
                        i.putExtra("OTPCode", otp);
                        if (i.resolveActivity(getPackageManager()) != null) {
                            startActivity(i);
                        }
                    }
                };
            }
        });*/
    }

    private void nextOtp(){
        inputOTP1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputOTP2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputOTP2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputOTP3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputOTP3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputOTP4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputOTP4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputOTP5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputOTP5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    inputOTP6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}