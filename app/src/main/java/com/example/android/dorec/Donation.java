package com.example.android.dorec;

public class Donation {
    private String name;
    private String address;
    private String thing;
    private String quantity;

    public Donation(String name, String address, String thing, String quantity){
        this.name = name;
        this.address = address;
        this.thing = thing;
        this.quantity = quantity;
    }

    public String getName() {return name;}
    public String getAddress() {return address;}
    public String getThing() { return thing; }
    public String getQuantity() { return quantity; }
}
