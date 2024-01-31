package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class Art_and_Design extends AppCompatActivity {

    String[] artCourses = {"Arts in Musicology" +
            " Arts in Music with Performance", "photography", "videography"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_and_design);


        // If you want to launch a new activity based on the selected item, you can use an Intent
        // Example:
        // if (selectedItem.equals("Item 1")) {
        //     Intent intent = new Intent(YourActivity.this, NewActivity.class);
        //     startActivity(intent);
        // }
    }
}
