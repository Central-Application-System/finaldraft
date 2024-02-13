package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class engineering extends AppCompatActivity {
    String[] artCourses = {"civil engineering" +
            " electrical engineering", "chemical engineering", "industrial engineering"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineering);

        TextView civil = findViewById(R.id.civil);
        TextView electrical = findViewById(R.id.electrical);
        TextView chemical = findViewById(R.id.chem_eng);
        TextView industial = findViewById(R.id.ind_eng);

        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of engineering", "civil engineering");
            }
        });

        electrical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of engineering", "electrical engineering");
            }
        });

        chemical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of engineering", "chemical engineering");
            }
        });

        industial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of engineering", "indudtrial engineering");
            }
        });
    }

    private void navigateToInstitutions(String selectedFaculty, String selectedCourse) {
        Intent intent = new Intent(engineering.this, institutions.class);
        intent.putExtra("selectedFaculty", selectedFaculty);
        intent.putExtra("selectedCourse", selectedCourse);
        startActivity(intent);
    }
}
