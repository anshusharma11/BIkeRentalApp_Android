package com.rent_app.rentb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by apadha on 2/5/16.
 */
public class ConfirmationRentActivity extends AppCompatActivity {
    private EditText editTextConfirmationRented;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_rented);
        editTextConfirmationRented = (EditText)findViewById(R.id.editTextConfirmation_rented);
        editTextConfirmationRented.setText("Congratulations!! your request has been updated in our system. You will also receive a confirmation email");


        Button homeButtonConfirmationRented = (Button)findViewById(R.id.homeButtonConfirmationRented);
        homeButtonConfirmationRented.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Congratulation for renting",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ConfirmationRentActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
