package com.rent_app.rentb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private static final String FIREBASE_URL = "https://blistering-fire-1295.firebaseio.com";//Daryls firebase https://burning-fire-9689.firebaseio.com/
    private Firebase firebaseRef;
    static final int REQUEST_TAKE_PHOTO = 10;
    ImageView imgView;
    private Button clickPhoto;
    public static final int IMAGE_IDENTIFIER = 10;
    private EditText firstName, lastName, emailAddress, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TextView tv = (TextView) findViewById(R.id.signuplink);
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(FIREBASE_URL);//setting firebase

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, logActivity.class);
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button b1 = (Button) findViewById(R.id.blogin);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToFireBase();
                Intent intent = new Intent(SignUpActivity.this, logActivity.class);
                startActivity(intent);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void sendDataToFireBase() {


        firebaseRef.createUser("heyyou@yahoo.com", "pass", new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "Fail"+firebaseError, Toast.LENGTH_SHORT).show();

                // there was an error
            }
        });




//        firstName = (EditText) findViewById(R.id.FirstName);
//        lastName = (EditText) findViewById(R.id.LasttName);
//        emailAddress = (EditText) findViewById(R.id.EmailId);
//        password = (EditText) findViewById(R.id.Password);
//
//
//
//        String fname = firstName.getText().toString();
//        String lname = lastName.getText().toString();
//        String email = emailAddress.getText().toString();
//        String pass = password.getText().toString();
//
//
//        User user = new User(fname,lname,email,pass);
//
//        firebaseRef.push().setValue(user);
        Toast.makeText(getApplicationContext(), "Welcome! Please sign in", Toast.LENGTH_SHORT).show();

    }


}
