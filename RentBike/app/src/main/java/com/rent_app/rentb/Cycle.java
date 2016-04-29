package com.rent_app.rentb;

/**
 * Created by apadha on 2/22/16.
 */

import android.media.Image;

import java.util.Date;

/**
 * Created by ccrodrig on 2/22/2016.
 */
public class Cycle {
    int id;
    private String owner;
    private String brand;
    private double priceperday;
    private String type;
    private String description;
    private String location;
    private Date toDate;
    private Date fromDate;
    private String accessories;
    private String image;



    private boolean available;

    public Cycle (){}


    public Cycle(String owner, String brand, double priceperday,
                 String type, String description, String location,
                 Date toDate, Date fromDate, boolean available, String accessories, String image) {
        this.owner = owner;
        this.brand = brand;
        this.priceperday = priceperday;
        this.type = type;
        this.description = description;
        this.location = location;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.available = available;
        this.accessories = accessories;
        this.image = image;
    }
    public String getOwner(){
        return owner;
    }

    public String getBrand() {
        return brand;
    }

    public double getPriceperday() {
        return priceperday;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getAccessories() {
        return accessories;
    }

    public String getLocation() {
        return location;
    }

    public Date getToDate() {
        return toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getImage() {
        return image;
    }
}