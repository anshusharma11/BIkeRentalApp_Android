package com.rent_app.rentb;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String FIREBASE_URL = "https://blistering-fire-1295.firebaseio.com";
    //Ansu:  https://blinding-torch-552.firebaseio.com/BikeFirebase
    private Firebase firebaseRef;
   static String cycleOwner  ="ALex" ;// Hard Coded User Will Ask Daryl for Help
    int counter = 0;
    String cycleBrand;
    final String albumName = "L11-camera-external-file";
    String mCurrentPhotoPath;;
   private EditText fromDateEtxt;
    private EditText toDateEtxt;
    static final int REQUEST_TAKE_PHOTO = 10;
    ImageView imgView;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    private Button clickPhoto;
    public static final int IMAGE_IDENTIFIER = 10;
    private EditText brandEdit,typeEdit, locationEdit, pricePerdayEdit, descritptionEdit, accessoriesEdit, etxt_fromdate, etxt_todate;

   private  DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
 String brand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        findViewsById();
        setDateTimeField();


        clickPhoto = (Button) findViewById(R.id.takePhoto);
        clickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent();

            }
        });

        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(FIREBASE_URL);

        findViewById(R.id.Add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToFireBase();
                Intent myIntent = new Intent(MainActivity.this, ConfirmationActivity.class);
               // myIntent.putExtra("Cycle owner", cycleOwner);
                Log.d("Activity Brand", brand);
                myIntent.putExtra("Cycle Brand", brand);
                startActivity(myIntent);

            }
        });
    }


    public void onClick(View view) {
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





    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);


            }
        }
    }



    public File createImageFile() throws IOException {
        File image = null;
        try{
            // Create an image file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getAlbumStorageDir();
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
        } catch (Exception e) {
            Log.e("anshu", "failed to create image file.  We will crash soon!");
        }
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();


        return image;
    }


    public File getAlbumStorageDir() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
        if (file.exists()) {
            Log.d("anshu", "Album directory exists");
        } else if (file.mkdirs()) {
            Log.i("anshu", "Album directory is created");
        } else {
            Log.e("anshu", "Failed to create album directory.  Check permissions and storage.");
        }
        return file;
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_IDENTIFIER){
            if(resultCode == RESULT_OK){
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 6;
                Bitmap b = BitmapFactory.decodeFile(mCurrentPhotoPath, options);

                //Display the captured image inside the ImageView
                imgView = (ImageView) findViewById(R.id.imageView);
                imgView.setImageBitmap(b);


            }
        }
    }


    public void sendDataToFireBase() {

        brandEdit = (EditText) findViewById(R.id.EditTextBrand);
        typeEdit = (EditText) findViewById(R.id.TypeEdit);
        pricePerdayEdit = (EditText) findViewById(R.id.pricePerdayEdit);
        descritptionEdit = (EditText) findViewById(R.id.descritptionEdit);
        accessoriesEdit = (EditText) findViewById(R.id.accessoriesEdit);
        locationEdit = (EditText) findViewById(R.id.LocationEdit);
        etxt_todate = (EditText) findViewById(R.id.etxt_todate);
        etxt_fromdate = (EditText) findViewById(R.id.etxt_fromdate);

        imgView = (ImageView) findViewById(R.id.imageView);
        imgView.buildDrawingCache();
        Bitmap bmp=imgView.getDrawingCache();

      //Bitmap bmp =  BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);//your image
       // Bitmap bmp =  BitmapFactory.decodeResource(getResources(),R.id.imageView);
        ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bYtE);
        bmp.recycle();
        byte[] byteArray = bYtE.toByteArray();
        String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);


        brand = brandEdit.getText().toString();
        String pricePerDayString = pricePerdayEdit.getText().toString();
        String description = descritptionEdit.getText().toString();
        String location = locationEdit.getText().toString();
        String toDateString = etxt_todate.getText().toString();
        String fromDateString = etxt_fromdate.getText().toString();
        String accessories = accessoriesEdit.getText().toString();
        String type = typeEdit.getText().toString();
        Date toDate = null;
        Date fromDate = null;
        Double pricePerDay = Double.parseDouble(pricePerDayString);

        try {
            toDate = dateFormat.parse(toDateString);
            fromDate = dateFormat.parse(fromDateString);

//5463651522
        } catch (Exception e) {

        }

        Boolean availability = true;


      Cycle cycle = new Cycle("Chris", brand, pricePerDay, type, description, location, toDate, fromDate, availability, accessories, imageFile);

        firebaseRef.push().setValue(cycle);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
