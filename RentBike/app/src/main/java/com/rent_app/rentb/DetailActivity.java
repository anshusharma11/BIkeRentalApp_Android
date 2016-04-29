package com.rent_app.rentb;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by apadha on 2/2/16.
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String FIREBASE_URL = "https://blistering-fire-1295.firebaseio.com";//Location for network
    private Firebase firebaseRef;

    private TextView EditTextDetailsBrand, EditTextDetailemail, EditTextdetailPricePerday, descritptionEdit, EditTextDetailNumberOfDays, EditTextDetailOwner, EditTextDetailsCondition;
    private ImageView BikeImage;
    private TextView ButtonPhoneDetails;
    private EditText fromDateEtxt;
    private EditText toDateEtxt;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    private DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final String key_of_selection = intent.getStringExtra(RentBike.EXTRA_MESSAGE);//!!!!!!!!!!!!!!

        firebaseRef = new Firebase(FIREBASE_URL);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();


        firebaseRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String temp = "";

                BikeImage = (ImageView)findViewById(R.id.bikeImage);
                DataSnapshot currentBikeImage = snapshot.child(key_of_selection).child("image");
                temp = currentBikeImage.getValue().toString();
                byte[] decodedString = Base64.decode(temp, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                BikeImage.setImageBitmap(decodedByte);


                EditTextDetailsBrand = (TextView)findViewById(R.id.EditTextDetailsBrand);
                DataSnapshot currentBikeBrand = snapshot.child(key_of_selection).child("brand");
                temp = currentBikeBrand.getValue().toString();
                EditTextDetailsBrand.setText(temp);

                EditTextdetailPricePerday = (TextView)findViewById(R.id.EditTextdetailPricePerday);
                DataSnapshot currentBikePrice = snapshot.child(key_of_selection).child("priceperday");
                temp = currentBikePrice.getValue().toString();
                EditTextdetailPricePerday.setText(temp);



                EditTextDetailNumberOfDays = (TextView)findViewById(R.id.EditTextDetailNumberOfDays);
                //Will require Date Calculation
                EditTextDetailNumberOfDays.setText("14");

                EditTextDetailOwner = (TextView)findViewById(R.id.EditTextDetailOwner);
                DataSnapshot currentBikeOwner = snapshot.child(key_of_selection).child("owner");
                temp = currentBikeOwner.getValue().toString();
                EditTextDetailOwner.setText(temp);

                EditTextDetailemail = (TextView)findViewById(R.id.EditTextDetailemail);
                EditTextDetailemail.setText("d@scu.edu");
                //Daryl
                ButtonPhoneDetails = (TextView)findViewById(R.id.phone);
                //more work req
                //Daryl
                EditTextDetailsCondition = (TextView)findViewById(R.id.EditTextDetailsCondition);
                DataSnapshot currentBikeDescription = snapshot.child(key_of_selection).child("description");
                temp = currentBikeDescription.getValue().toString();
                EditTextDetailsCondition.setText(temp);

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

                                          });



        Button homeButtonDeatils = (Button)findViewById(R.id.homeButtonDeatils);
        homeButtonDeatils.setOnClickListener(new View.OnClickListener() {
//
//
            @Override
            public void onClick(View view) {
//
                Intent intent = new Intent(DetailActivity.this, ConfirmationActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onClick(View view){
        if (view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if (view == toDateEtxt) {
            toDatePickerDialog.show();
        }

    }





    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.etxt_fromdate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (EditText) findViewById(R.id.etxt_todate);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);
        toDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
        toDatePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());


    }
}