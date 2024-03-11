package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class subjectsList extends AppCompatActivity {

    private Map<String, Integer> gradePointMap;
    private Button studentNext;
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private ArrayList<getsubject> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_list);

        recyclerView = findViewById(R.id.subjectlist);
        database = FirebaseDatabase.getInstance().getReference("subjects");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(this, list, new MyAdapter.OnOptionSelectedListener() {
            @Override
            public void onOptionSelected(int position, String selectedOption) {
                // Handle the option selected event
            }
        });
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Check if the data is a string
                    if (dataSnapshot.getValue() instanceof String) {
                        String subject = dataSnapshot.getValue(String.class);

                        // Create a new getsubject object and set the subject
                        getsubject subjectObject = new getsubject();
                        subjectObject.setSubjects(subject);

                        // Add the object to the list
                        list.add(subjectObject);
                    } else {
                        // Handle other cases (if any)
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DatabaseError", "Error fetching data from Firebase: " + error.getMessage());
            }
        });

        studentNext = findViewById(R.id.studentNext);

        studentNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculate total points for the best six results
                int totalPoints = calculateTotalPointsForBestSix();

                // Navigate to the activity_pdf_upload activity and pass the totalPoints
                Intent intent = new Intent(subjectsList.this, PdfUploadActivity.class);
                intent.putExtra("totalPoints", totalPoints);
                startActivity(intent);
            }
        });
    }

    private int calculateTotalPointsForBestSix() {
        int totalPoints = 0;
        for (int i = 0; i < myAdapter.getItemCount(); i++) {
            int subjectPoints = myAdapter.calculateTotalPoints(i);
            Log.d("CalculationDebug", "Subject " + i + " Points: " + subjectPoints);
            totalPoints += subjectPoints;
        }

        Log.d("CalculationDebug", "Total Points: " + totalPoints);
        return totalPoints;
    }
}