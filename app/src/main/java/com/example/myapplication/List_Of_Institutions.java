package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class List_Of_Institutions extends AppCompatActivity {

    String institutionList[]= {"Eswatini College Of Technology", "Christian Medical University", "University Of Eswatini", "Limkokwing University", "William Pitcher College", "Southern Nazarene University"};
    int intimage []= {R.drawable.scot, R.drawable.emcu, R.drawable.kwalu, R.drawable.limko, R.drawable.william, R.drawable.sanu};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_institutions);

        listView = findViewById(R.id.listInstitutions);
        custombaseadapter customBaseAdapter = new custombaseadapter(getApplicationContext(), institutionList, intimage);
        listView.setAdapter(customBaseAdapter);

        // Set click listener to the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected institution
                String selectedInstitution = institutionList[position];

                // Start the FacultyActivity and pass the selected institution as an extra
                Intent intent = new Intent(List_Of_Institutions.this, FacultyActivity.class);
                intent.putExtra("institution", selectedInstitution);
                startActivity(intent);
            }
        });
    }
}