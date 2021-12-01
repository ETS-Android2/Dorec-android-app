package com.example.android.dorec;

public class Donation {
    private String name;
    private String location;
    private String date;
    private String type;
    private String quantity;

    //Getter for name of Institution/Volunteer
    public String getName(){
        return this.name;
    }
    //Setter for name of Institution/Volunteer
    public void setName(String name){
        this.name = name;
    }

    //Getter for location of donation
    public String getLocation() {
        return this.location;
    }
    //Setter for location of donation
    public void setLocation(String location) {
        this.location = location;
    }

    //Getter for date of donation
    public String getDate() {
        return this.date;
    }
    //Setter for date of Donation
    public void setDate(String date) {
        this.date = date;
    }

    //Getter for type of Donation
    public String getType() {
        return this.type;
    }
    //Setter for type of Donation
    public void setType(String type) {
        this.type = type;
    }

    //Getter for quantity of Donation
    public String getQuantity() {
        return this.quantity;
    }
    //Setter for quantity of Donation
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    //Constructor
    public Donation(String name, String location, String date, String type, String quantity){
        this.name = name;
        this.location = location;
        this.date = date;
        this.type = type;
        this.quantity = quantity;
    }
}
