package com.rent_app.rentb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class myListings extends AppCompatActivity {
    List<Bike> bikes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listings);

        bikes = new ArrayList<>();
        bikes.add(new Bike("Specialized - Road Bike\nstatus:", "myListedBike.png"));
        bikes.add(new Bike("Giant - Mountain Bike\nstatus:", "myListedBike.png"));
//        bikes.add(new Bike("Pink Pig", "pig.png"));
//        bikes.add(new Bike("Orange Tiger", "tiger.png"));
//        bikes.add(new Bike("Green Turtle", "turtle.png"));

        ListView lv = (ListView) findViewById(R.id.myListings);
        lv.setAdapter(new ListingBikeArrayAdaptor(this, R.layout.listing_custom_row, bikes));
//        lv.setOnItemClickListener(this);
    }
}
