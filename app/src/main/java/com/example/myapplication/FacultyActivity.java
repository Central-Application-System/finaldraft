package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacultyActivity extends AppCompatActivity {

    // Define faculties for each institution
    private Map<String, List<String>> institutionFaculties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        // Initialize institutionFaculties map
        initializeInstitutionFaculties();

        // Get the selected institution from the intent
        String institution = getIntent().getStringExtra("institution");

        // Display the selected institution in a TextView
        TextView institutionTextView = findViewById(R.id.institutionTextView);
        institutionTextView.setText(institution);

        // Get faculties specific to the selected institution
        List<String> faculties = institutionFaculties.get(institution);

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faculties);

        // Find the ListView and set the adapter
        ListView facultyListView = findViewById(R.id.facultyListView);
        facultyListView.setAdapter(adapter);

        // Set click listener to the ListView
        facultyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked faculty
                String faculty = faculties.get(position);

                // Navigate to the CoursesActivity with selected faculty
                Intent intent = new Intent(FacultyActivity.this, CoursesActivity.class);
                intent.putExtra("institution", institution);
                intent.putExtra("faculty", faculty);
                startActivity(intent);
            }
        });

        Button applyButton = findViewById(R.id.applyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FacultyActivity.this, payment.class);
                startActivity(intent);
            }
        });
    }

    // Initialize faculties for each institution
    private void initializeInstitutionFaculties() {
        institutionFaculties = new HashMap<>();

        // Add faculties for each institution
        List<String> scotFaculties = new ArrayList<>();
        scotFaculties.add("Education");
        scotFaculties.add("Building and Civil");
        scotFaculties.add("Information Communication Technology");
        scotFaculties.add("Business");
        scotFaculties.add("Engineering and Science ");
        institutionFaculties.put("Eswatini College Of Technology", scotFaculties);

        // Add faculties for other institutions similarly
        // For example:
        // List<String> emcuFaculties = new ArrayList<>();
        // emcuFaculties.add("Faculty 1");
        // emcuFaculties.add("Faculty 2");
        // institutionFaculties.put("Christian Medical University", emcuFaculties);
    }
}