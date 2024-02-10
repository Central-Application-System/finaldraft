package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class results extends AppCompatActivity {
    private Map<String, String> subjectGradeMap = new HashMap<>();
    private EditText editTextSubject1, editTextGrade1, editTextSubject2, editTextGrade2,
            editTextSubject3, editTextGrade3, editTextSubject4, editTextGrade4,
            editTextSubject5, editTextGrade5, editTextSubject6, editTextGrade6;
    private Button buttonUploadResults;
    private TextView textViewTotalPoints;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Initialize UI elements
        editTextSubject1 = findViewById(R.id.subject_1);
        editTextGrade1 = findViewById(R.id.grade_1);
        editTextSubject2 = findViewById(R.id.subject_2);
        editTextGrade2 = findViewById(R.id.grade_2);
        editTextSubject3 = findViewById(R.id.subject_3);
        editTextGrade3 = findViewById(R.id.grade_3);
        editTextSubject4 = findViewById(R.id.subject_4);
        editTextGrade4 = findViewById(R.id.grade_4);
        editTextSubject5 = findViewById(R.id.subject_5);
        editTextGrade5 = findViewById(R.id.grade_5);
        editTextSubject6 = findViewById(R.id.subject_6);
        editTextGrade6 = findViewById(R.id.grade_6);
        buttonUploadResults = findViewById(R.id.buttonUploadResults);
        textViewTotalPoints = findViewById(R.id.total);

        // Set onClickListener for the Upload Results button
        buttonUploadResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int totalPoints = calculateTotalPoints();
                    uploadResultsToFirebase(totalPoints);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(results.this, "Error uploading results", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int calculateTotalPoints() {
        // Populate subject-grade map
        populateSubjectGradeMap();

        // Calculate total points
        int totalPoints = 0;
        for (Map.Entry<String, String> entry : subjectGradeMap.entrySet()) {
            int points = convertGradeToPoints(entry.getValue());
            totalPoints += points;
        }

        // Display the total points
        textViewTotalPoints.setText("Total Points: " + totalPoints);

        return totalPoints;
    }

    private void populateSubjectGradeMap() {
        subjectGradeMap.clear();
        subjectGradeMap.put(editTextSubject1.getText().toString(), editTextGrade1.getText().toString());
        subjectGradeMap.put(editTextSubject2.getText().toString(), editTextGrade2.getText().toString());
        subjectGradeMap.put(editTextSubject3.getText().toString(), editTextGrade3.getText().toString());
        subjectGradeMap.put(editTextSubject4.getText().toString(), editTextGrade4.getText().toString());
        subjectGradeMap.put(editTextSubject5.getText().toString(), editTextGrade5.getText().toString());
        subjectGradeMap.put(editTextSubject6.getText().toString(), editTextGrade6.getText().toString());
    }

    private void uploadResultsToFirebase(int totalPoints) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();

            DatabaseReference resultsReference = FirebaseDatabase.getInstance().getReference("results");

            // Create a map to store the results data
            Map<String, Object> resultsData = new HashMap<>();
            resultsData.put("subject1", editTextSubject1.getText().toString());
            resultsData.put("grade1", editTextGrade1.getText().toString());
            resultsData.put("subject2", editTextSubject2.getText().toString());
            resultsData.put("grade2", editTextGrade2.getText().toString());
            resultsData.put("subject3", editTextSubject3.getText().toString());
            resultsData.put("grade3", editTextGrade3.getText().toString());
            resultsData.put("subject4", editTextSubject4.getText().toString());
            resultsData.put("grade4", editTextGrade4.getText().toString());
            resultsData.put("subject5", editTextSubject5.getText().toString());
            resultsData.put("grade5", editTextGrade5.getText().toString());
            resultsData.put("subject6", editTextSubject6.getText().toString());
            resultsData.put("grade6", editTextGrade6.getText().toString());
            resultsData.put("totalPoints", totalPoints);

            resultsReference.child(userId).updateChildren(resultsData);

            Toast.makeText(results.this, "Results uploaded successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(results.this, PdfUploadActivity.class));
        } else {
            // Handle the case where the user is not authenticated
            Toast.makeText(results.this, "User not authenticated", Toast.LENGTH_SHORT).show();
            // You may want to redirect the user to the sign-in page or handle it accordingly.
        }
    }
    private int convertGradeToPoints(String grade) {

        switch (grade.toUpperCase()) {
            case "A":
                return 8;
            case "B":
                return 7;
            case "C":
                return 6;
            case "D":
                return 5;
            case "E":
                return 4;
            case "F":
                return 3;
            case "U":
                return 1;
            default:
                return 0;
        }
    }
}