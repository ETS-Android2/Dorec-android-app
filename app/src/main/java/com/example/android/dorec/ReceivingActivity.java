package com.example.android.dorec;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ReceivingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving);

        ArrayList<Donation> donations = new ArrayList<Donation>();
        donations.add(new Donation("Manohar Palace", "Plot no. 344, Zone-2, M.P. Nagar, Bhopal", "Food", "3 kg of Butter Paneer Masala, 30 Tava Roti"));
        donations.add(new Donation("Vedant Pathology", "Opposite Lalghati Domino's, Lalghati, Bhopal", "Medicine", "5 Doses of Remdesiver injection"));
        donations.add(new Donation("Ronny Sharma", "House no. 23, Premium Orchard, Ayodhya", "Books", "Class 12th (Science) C.B.S.E. Books"));
        donations.add(new Donation("Ruhi Singh", "House no. 204, Railway Colony, Bhopal", "Clothes", "3 tops, 3 jeans, for adults (L-size)"));
        donations.add(new Donation("Bapu ki Kutiya", "Sector C, Indrapuri, Bhopal", "Food", "3 kg Butter Chicken, Butter Naan"));
        donations.add(new Donation("Vivek Vadhwa", "Jeet Homes, Vrindavan Nagar, Ayodhya Bypass Rd., Bhopal", "Books", "Class 10th (Science) I.C.S.E"));

        DonationAdapter donationAdapter = new DonationAdapter(this, donations);
        ListView listView = (ListView) findViewById(R.id.listOfDonations);
        listView.setAdapter(donationAdapter);
    }
}