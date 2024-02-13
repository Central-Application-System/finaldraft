package com.example.myapplication;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class institutions extends AppCompatActivity {

    // Array of institutions (replace with your actual institutions)
    private String[] institutions = {"Eswatini University", "Limkokwing University", "Christian University", "Southern Africa Nazarene University", "William Pitcher College", "Eswatini College of Technology"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutions);

        // Retrieve the selected course information
        String selectedCourse = getIntent().getStringExtra("SELECTED_COURSE");

        // Display institutions based on the selected course
        displayInstitutions(selectedCourse);
    }

    private void displayInstitutions(String selectedCourse) {
        // Here, implement logic to display the relevant institutions
        // You can use if-else statements to determine the institutions based on the course

        // Create an ArrayAdapter to populate the ListView with institutions
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, institutions);

        // Get a reference to the ListView
        ListView institutionsListView = findViewById(R.id.institutionsListView);

        // Set the ArrayAdapter on the ListView
        institutionsListView.setAdapter(adapter);

        // Set an item click listener to handle when an institution is clicked
        institutionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                // Get the selected institution
                String selectedInstitution = institutions[position];

                // Perform an action based on the selected institution (e.g., show details)
                Toast.makeText(institutions.this, "Selected Institution: " + selectedInstitution, Toast.LENGTH_SHORT).show();
            }
        });
    }
}