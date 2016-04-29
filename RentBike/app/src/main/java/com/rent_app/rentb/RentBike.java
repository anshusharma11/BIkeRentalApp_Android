package com.rent_app.rentb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.Query;

import java.util.ArrayList;
import java.util.List;

//Displays List of Available Bicycles for rent
public class RentBike extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String FIREBASE_URL = "https://blistering-fire-1295.firebaseio.com";//Location for network
    private Firebase firebaseRef;
    public final static String EXTRA_MESSAGE = "com.rent_app.rentb";
    DataSnapshot dataSnapshot;
    String subtitle = "";
    String key = "";
    String imageBit64 = "";

    List<Bicycle> bicycles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(FIREBASE_URL);//instantiation



        super.onCreate(savedInstanceState);
        setContentView(R.layout.bike_list);


        // build animal list
        bicycles = new ArrayList<>();

        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Cycle post = postSnapshot.getValue(Cycle.class);
                    subtitle = post.getType() + " $" + post.getPriceperday() + "/day";
                    key = postSnapshot.getKey();// Requesting key
                    imageBit64 = post.getImage();
                    System.out.println("Image File: " + imageBit64);
                    byte[] decodedString = Base64.decode(imageBit64, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    //Retrieve image and converted to Bitmap
//                    Toast.makeText(getApplicationContext(), "Key Values : " + key, Toast.LENGTH_SHORT).show();

                    bicycles.add(new Bicycle(post.getBrand(), decodedByte, subtitle, key));



                }
                setList();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });
    }

    public void setList(){
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new BicycleArrayAdapter(this, R.layout.custom_list, bicycles));
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bicycle selection = bicycles.get(position);
//        Toast.makeText(getApplicationContext(), "Bike Selected : " + animal.getName(), Toast.LENGTH_SHORT).show();
        String selected = selection.getKey();
        Intent intent = new Intent(RentBike.this, DetailActivity.class);
        Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show();

        intent.putExtra(EXTRA_MESSAGE, selected);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_profile:
                Intent profile = new Intent(RentBike.this,profile.class);
                startActivity(profile);
                break;

            case R.id.menu_listings:

                Intent listings = new Intent(RentBike.this, myListings.class);
                startActivity(listings);
                break;


            case R.id.menu_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(RentBike.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to log out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent newActivity5 = new Intent(RentBike.this, logActivity.class);
                        startActivity(newActivity5);

                    }
                });
                builder.setNegativeButton("No", null);

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}



