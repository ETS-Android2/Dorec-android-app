package com.example.android.dorec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DonationAdapter extends ArrayAdapter<Donation> {
    public DonationAdapter(Context context, ArrayList<Donation> donations){
        super(context, 0, donations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = convertView;

        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_of_donations, parent, false);
        }

        Donation currentDonation = (Donation) getItem(position);

        TextView name = (TextView) itemView.findViewById(R.id.nameTV);
        name.setText(currentDonation.getName());

        TextView address = (TextView) itemView.findViewById(R.id.addressTV);
        address.setText(currentDonation.getLocation());

        TextView thing = (TextView) itemView.findViewById(R.id.thingTV);
        thing.setText(currentDonation.getType());

        TextView quantity = (TextView) itemView.findViewById(R.id.quantityTV);
        quantity.setText(currentDonation.getQuantity());

        return itemView;
    }
}
