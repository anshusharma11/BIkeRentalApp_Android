package com.rent_app.rentb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class profile extends AppCompatActivity {

    List<Bike> bikes;

    ListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Build Bike List
        bikes = new ArrayList<>();
        bikes.add(new Bike("Specialized - Road Bike", "specialized.png"));
        bikes.add(new Bike("Giant - Mountain Bike", "giant.png"));
//        bikes.add(new Bike("Pink Pig", "pig.png"));
//        bikes.add(new Bike("Orange Tiger", "tiger.png"));
//        bikes.add(new Bike("Green Turtle", "turtle.png"));

        ListView lv = (ListView) findViewById(R.id.myList);
        lv.setAdapter(new BikeArrayAdaptor(this, R.layout.profile_custom_row, bikes));
//        lv.setOnItemClickListener(this);


    }
}
