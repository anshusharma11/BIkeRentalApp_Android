package com.rent_app.rentb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


public class logActivity extends AppCompatActivity {
    private static final String FIREBASE_URL = "https://blistering-fire-1295.firebaseio.com";//
    private Firebase firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Firebase.setAndroidContext(this);


      final  EditText e1 = (EditText) findViewById(R.id.emailid);
       final EditText e2 = (EditText) findViewById(R.id.password);
        Button b1 = (Button) findViewById(R.id.blogin);

        TextView link =(TextView)findViewById(R.id.signuplink);


        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(logActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /// Trying Firebase Auth method
                String e1resp = e1.getText().toString();
                String e2resp = e2.getText().toString();

                firebaseRef = new Firebase(FIREBASE_URL);

                firebaseRef.authWithPassword(e1resp, e2resp, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                        Intent intent = new Intent(logActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "Error Loging in: "+firebaseError, Toast.LENGTH_SHORT).show();


                        // there was an error
                    }
                });

                /////end method


                //method 2

                //method 2 end





//                if(e1.getText().toString().equals("mary@gmail.com") && e2.getText().toString().equals("mary123")){
//
//
//                    Intent intent = new Intent(logActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                }else{
//                    Toast.makeText(getApplicationContext(), "Please check your credentials and try again.",
//                            Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
