package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

        TextView musicologyTextView = findViewById(R.id.musicology);
        TextView performanceTextView = findViewById(R.id.perfomance);
        TextView photographyTextView = findViewById(R.id.photography);
        TextView videographyTextView = findViewById(R.id.vidiography);

        musicologyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of Arts", "Arts in Musicology");
            }
        });

        performanceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of Arts", "Arts in Music with Performance");
            }
        });

        photographyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of Arts", "Arts in photography");
            }
        });

        videographyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of Arts", "Arts in vidiography");
            }
        });
    }

    private void navigateToInstitutions(String selectedFaculty, String selectedCourse) {
        Intent intent = new Intent(Art_and_Design.this, institutions.class);
        intent.putExtra("selectedFaculty", selectedFaculty);
        intent.putExtra("selectedCourse", selectedCourse);
        startActivity(intent);
    }
}



