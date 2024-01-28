package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class studentregistration extends AppCompatActivity {

    EditText signupemail,signuppassword,signupfullname,signupusername;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button register;
Button result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentregistation);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        signupfullname = findViewById(R.id.fullname);
        signupemail = findViewById(R.id.email);
        signuppassword = findViewById(R.id.password);
        signupusername = findViewById(R.id.cpasswordett);

        register = findViewById(R.id.resultet);

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Ensure that the EditText fields are not null
                if (signuppassword != null && signupfullname != null &&
                        signupemail != null && signupusername != null) {

                    // Retrieve user input
                    String password = signuppassword.getText().toString();
                    String fullname = signupfullname.getText().toString();
                    String email = signupemail.getText().toString();
                    String username = signupusername.getText().toString();

                    // Ensure that none of the retrieved strings are null or empty
                    if (password != null && !password.isEmpty() &&
                            fullname != null && !fullname.isEmpty() &&
                            email != null && !email.isEmpty() &&
                            username != null && !username.isEmpty()) {

                        // Create a helperClass object
                        helperClass helperClass = new helperClass(fullname, password, username, email);

                        // Ensure that the Firebase Database objects are not null
                        if (database != null && reference != null) {
                            // Save data to Firebase Database
                            reference.child(username).setValue(helperClass);

                            // Display a toast message
                            Toast.makeText(studentregistration.this, "Registration successful", Toast.LENGTH_SHORT).show();

                            // Start a new activity
                            Intent intent = new Intent(studentregistration.this, questions.class);
                            startActivity(intent);
                        } else {
                            // Handle the case where database or reference is null
                            Log.e("NullCheck", "Firebase Database objects are null");
                        }
                    } else {
                        // Handle the case where any of the retrieved strings is null or empty
                        Log.e("NullCheck", "One or more input fields are null or empty");
                    }
                } else {
                    // Handle the case where any of the EditText fields is null
                    Log.e("NullCheck", "One or more EditText fields are null");
                }
            }
        });

}
    }