package com.example.myapplication;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CoursesActivity extends AppCompatActivity {
    private static final String SERVER_URL = "http://192.168.8.156:80/addCourse";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final String PREFS_NAME = "MyPrefs";
    private static final String SELECTED_COURSES_KEY = "selectedCourses";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        // Get the selected institution and faculty from the intent
        String institution = getIntent().getStringExtra("institution");
        String faculty = getIntent().getStringExtra("faculty");

        // Display the selected institution and faculty in TextViews
        TextView institutionTextView = findViewById(R.id.institutionTextView);
        institutionTextView.setText(institution);
        TextView facultyTextView = findViewById(R.id.facultyTextView);
        facultyTextView.setText(faculty);

        // Dummy data for courses (replace with your actual data)
        ArrayList<String> courses = new ArrayList<>();
        // Populate courses based on the selected faculty
        if (faculty.equals("Education")) {
            courses.add("ICT Education");
            courses.add("Secondary Teaching");
            // Add more courses as needed
        } else if (faculty.equals("Building and Civil")) {
            courses.add("Civil Engineering");
            courses.add("Architecture");

        } else if (faculty.equals("Information Communication Technology")) {
            courses.add("Computer Science");
        } else if (faculty.equals("Business")) {
            courses.add("Business Administration");
            courses.add("Human ResourceManagement");
            courses.add("Office Management Technology");
            courses.add("Hospitality");
            courses.add("Tourism Management");
            courses.add("Marketing");
        } else if (faculty.equals("Engineering and Science")) {
            courses.add("Mechanical Engineering");
            courses.add("Electrical and Electronics Engineering");
            courses.add("Automotive Engineering(light)");
            courses.add("Automotive Engineering(heavy)");
            courses.add("Vehicle Body Repair");
            courses.add("Vehicle Body Repair");
        }

        // Create an ArrayAdapter to populate the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);

        // Find the ListView and set the adapter
        ListView courseListView = findViewById(R.id.courseListView);
        courseListView.setAdapter(adapter);

        // Set click listener for the ListView items
        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked course
                String course = courses.get(position);

                // Create a confirmation message
                String message = "You are applying for " + course + ". Are you sure?";

                // Create a dialog box for confirmation
                AlertDialog.Builder builder = new AlertDialog.Builder(CoursesActivity.this);
                builder.setTitle("Confirm Application")
                        .setMessage(message)
                        .setPositiveButton("Apply", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendSelectedCourseToServer(course);
                            }
                        })
                        .setNegativeButton("Cancel", null) // Do nothing on cancel
                        .show();
            }
        });
    }

    private void sendSelectedCourseToServer(String course) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String json = "{\"courseName\": \"" + course + "\"}";
                RequestBody body = RequestBody.create(json, JSON);
                Request request = new Request.Builder()
                        .url(SERVER_URL)
                        .post(body)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        // Handle success response if needed
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Store the selected course in SharedPreferences
                                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putString(SELECTED_COURSES_KEY, course);
                                editor.apply();
                                // Finish the activity and go back to the previous screen
                                finish();
                            }
                        });
                    } else {
                        // Handle unsuccessful response if needed
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CoursesActivity.this, "Failed to apply for the course", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CoursesActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }
}