package com.rent_app.rentb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

/**
 * Created by apadha on 2/2/16.
 */
public class ConfirmationActivity extends AppCompatActivity {

    private EditText editTextConfirmation;
    String cycleBrand;
    Firebase ref = new Firebase("https://blinding-torch-552.firebaseio.com/BikeFirebase");


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        try{
            Bundle extras = getIntent().getExtras();
             cycleBrand = extras.getString("brand");
            Log.d("cycleBrand", cycleBrand);
        }catch(Exception e){

        }

        editTextConfirmation = (EditText)findViewById(R.id.editTextConfirmation);
        editTextConfirmation.setText("Congratulations!! your request has been updated in our system You will also receive a confirmation email");


        Button homeButtonConfirmation = (Button)findViewById(R.id.homeButtonConfirmation);
        homeButtonConfirmation.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Your bike has been added successfully",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ConfirmationActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });



    }
}