package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class studentregistration extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextInputEditText fullNameEditText, passwordEditText, confirmPasswordEditText, emailEditText;



    FirebaseDatabase database;
    DatabaseReference reference;
Button result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentregistation);


        firebaseAuth = FirebaseAuth.getInstance();
        fullNameEditText = findViewById(R.id.fullname);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.cpasswordett);
        emailEditText = findViewById(R.id.email);
        MaterialButton registerButton = findViewById(R.id.resultet);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
    private void registerUser() {
        String fullName = fullNameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();

        // Check if fields are not empty
        if (fullName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if password and confirm password match
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new user with email and password
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        // Additional user data
                        User newUser = new User();
                        newUser.setFullName(fullName);
                        newUser.setEmail(email);

                        // Store additional user data in the Realtime Database
                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                        usersRef.child(user.getUid()).setValue(newUser);

                        // Registration success, update UI or navigate to another activity
                        Toast.makeText(studentregistration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(studentregistration.this, sign_in.class));
                        finish();// Close the current activity to prevent going back to registration screen
                    } else {
                        // If registration fails, display a message to the user
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidUserException e) {
                            // Handle invalid user exception (e.g., invalid email format)
                            Toast.makeText(studentregistration.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            // Handle invalid credentials exception (e.g., weak password)
                            Toast.makeText(studentregistration.this, "Weak password, please choose a stronger one", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthException e) {
                            // Handle other FirebaseAuthExceptions
                            Toast.makeText(studentregistration.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            // Handle other exceptions
                            Toast.makeText(studentregistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    }