package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class questions extends AppCompatActivity {

    String[] courses = {"Humanities", "IT", "Engineering", "Art and Design", "Business", "Sciences", "Communications/Media"};

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quetions);

        autoCompleteTextView = findViewById(R.id.questionet);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, courses);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCourse = parent.getItemAtPosition(position).toString();
                handleCourseSelection(selectedCourse);
            }
        });
    }

    private void handleCourseSelection(String selectedCourse) {
        // Check the selected course and launch the relevant activity
        switch (selectedCourse) {
            case "Humanities":
                startActivity(new Intent(questions.this,humanities.class));
                break;
            case "IT":
                startActivity(new Intent(questions.this, IT_course.class));
                break;
            case "Engineering":
                startActivity(new Intent(questions.this, engineering.class));
                break;
            case "Art and Design":
                startActivity(new Intent(questions.this, Art_and_Design.class));
                break;
            case "Business":
                startActivity(new Intent(questions.this, business.class));
                break;
            case "Sciences":
                startActivity(new Intent(questions.this, sciences.class));
                break;

            default:
                // Handle default case or show a message
                break;
        }
    }
}