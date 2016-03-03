package com.mobileappscompany.training.recycler;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private List<Person> people;
    private List<Person> updatedPeople;
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private String imgDecodableString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Person> books = Person.listAll(Person.class);

        if(books!= null && books.size()<=0){


            people = new ArrayList<Person>();
            people.add(new Person("Paty", "Feeling cool", R.drawable.anime1, randomDate()));
            people.add(new Person("Jen", "IM hungry", R.drawable.anime2,randomDate()));
            people.add(new Person("Lui", "Going out tonigh", R.drawable.anime3,randomDate()));
            people.add(new Person("Jocelyn", "Miss me?  ", R.drawable.anime4,randomDate()));
            people.add(new Person("Rachel", "Everithin ok", R.drawable.anime5, randomDate()));
            people.add(new Person("Yissel", "add meeeee", R.drawable.anime6, randomDate()));
            people.add(new Person("Monica", "Not so good", R.drawable.anime7, randomDate()));
            people.add(new Person("Yadira", "Im your doll", R.drawable.anime8, randomDate()));

            SugarRecord.saveInTx(people);


        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }




    public String randomDate(){

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2000, 2016);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return  (gc.get(gc.MONTH) + 1) + "-"  + gc.get(gc.DAY_OF_MONTH) + "-" +  gc.get(gc.YEAR)  ;


    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }


    public void loadImg(View view) {

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
        startActivityForResult(galleryIntent, SELECT_PICTURE);

        //startActivityForResult(Intent.createChooser(intent,"Select file to upload "), SELECT_FILE1);


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imageId);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }






    public void saveImageButton(View view) {

        EditText name = (EditText) findViewById(R.id.fullName);
        EditText status = (EditText) findViewById(R.id.status);

        ImageView imag = (ImageView) findViewById(R.id.imageId);

        Person p = new Person(name.getText().toString(), status.getText().toString(), imag.getId(), "hjhjh");

        Person.saveInTx(p);




    }
}
