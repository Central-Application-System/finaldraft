package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        Log.d("YourTag", "Selected Course in questions: " + selectedCourse);
        // Check the selected course and launch the relevant activity
        Class<?> courseActivityClass = getCourseActivityClass(selectedCourse);

        if (courseActivityClass != null) {
            Log.d("YourTag", "Launching activity: " + courseActivityClass.getSimpleName());
            Intent intent = new Intent(questions.this, courseActivityClass);
            intent.putExtra("SELECTED_COURSE", selectedCourse);
            startActivity(intent);
        } else {
            // Handle default case or show a message
            Log.e("YourTag", "CourseActivityClass is null for course: " + selectedCourse);
        }
    }
    private Class<?> getCourseActivityClass(String selectedCourse) {
        // Implement a logic to map each faculty to its corresponding CourseActivity class
        // Return the Class of the corresponding CourseActivity
        switch (selectedCourse) {
            case "Humanities":
                return humanities.class;
            case "IT":
                return IT_course.class;
            case "Engineering":
                return engineering.class;
            case "Art and Design":
                return Art_and_Design.class;
            case "Business":
                return business.class;
            case "Sciences":
                return sciences.class;
            default:
                return null;
        }
    }
}