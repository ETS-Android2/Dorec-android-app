package com.example.android.dorec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ReceivingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);

        ArrayList<Donation> donations = new ArrayList<Donation>();

        donations.add(new Donation("Manohar Palace", "Plot no. 344, Zone-2, M.P. Nagar, Bhopal", "1 Dec 2021","Food", "3 kg of Butter Paneer Masala, 30 Tava Roti"));
        donations.add(new Donation("Vedant Pathology", "Opposite Lalghati Domino's, Lalghati, Bhopal", "30 Nov 2021", "Medicine", "5 Doses of Remdesiver injection"));
        donations.add(new Donation("Ronny Sharma", "House no. 23, Premium Orchard, Ayodhya", "1 Dec 2021", "Books", "Class 12th (Science) C.B.S.E. Books"));
        donations.add(new Donation("Ruhi Singh", "House no. 204, Railway Colony, Bhopal", "1 Dec 2021", "Clothes", "3 tops, 3 jeans, for adults (L-size)"));
        donations.add(new Donation("Bapu ki Kutiya", "Sector C, Indrapuri, Bhopal", "30 Nov 2021", "Food", "3 kg Butter Chicken, Butter Naan"));
        donations.add(new Donation("Vivek Vadhwa", "Jeet Homes, Vrindavan Nagar, Ayodhya Bypass Rd., Bhopal", "22 Nov 2021", "Books", "Class 10th (Science) I.C.S.E"));

        DonationAdapter donationAdapter = new DonationAdapter(this, donations);
        ListView listView = (ListView) findViewById(R.id.listOfDonations);
        listView.setAdapter(donationAdapter);
    }
}