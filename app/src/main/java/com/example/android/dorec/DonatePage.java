package com.example.android.dorec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class DonatePage extends AppCompatActivity {
    private static final String LOG_TAG = DonatePage.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_page);

        TextView nameOfInstitution = (TextView) findViewById(R.id.nameOfInstitutionID);
        TextView locationForReceiving = (TextView) findViewById(R.id.locationForReceiving);
        TextView dateOfDonation = (TextView) findViewById(R.id.dateOfDonationID);
        TextView typeOfDonation = (TextView) findViewById(R.id.typeOfDonationID);
        TextView quantityOfDonation = (TextView) findViewById(R.id.quantityOfDonationID);

        EditText nameOfInstitutionInput = (EditText) findViewById(R.id.nameOfInstitutionInput);
        EditText locationForReceivingInput = (EditText) findViewById(R.id.locationForReceivingInput);
        EditText dateOfDonationInput = (EditText) findViewById(R.id.dateOfDonationInput);
        EditText typeOfDonationInput = (EditText) findViewById(R.id.typeOfDonationInput);
        EditText quantityOfDonationInput = (EditText) findViewById(R.id.quantityOfDonationInput);

        TextView submitDonationBtn = (TextView) findViewById(R.id.submitDonationBtn);

        submitDonationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameOfInstitutionInput.getText().toString();
                String location = locationForReceivingInput.getText().toString();
                String date = dateOfDonationInput.getText().toString();
                String type = typeOfDonationInput.getText().toString();
                String quantity = quantityOfDonationInput.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Donation donation = new Donation(name, location, date, type, quantity);

                //Add donation to Hashmap to be sent to database
                HashMap<String, String> donationToSend = new HashMap<>();
                donationToSend.put("name", donation.getName());
                donationToSend.put("location", donation.getLocation());
                donationToSend.put("date", donation.getDate());
                donationToSend.put("type", donation.getType());
                donationToSend.put("quantity", donation.getQuantity());

                //Add donation to "donations" collection in Firestore database
                db.collection("donations").add(donationToSend)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(LOG_TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(LOG_TAG, "Error adding donation", e);
                            }
                        });
                nameOfInstitutionInput.setText("");
                locationForReceivingInput.setText("");
                dateOfDonationInput.setText("");
                typeOfDonationInput.setText("");
                quantityOfDonationInput.setText("");

                Toast.makeText(v.getContext(), "Data has been submitted", Toast.LENGTH_LONG).show();
            }
        });
    }
}