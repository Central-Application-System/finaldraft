package com.example.myapplication;

import static com.example.myapplication.R.id.create_button;
import static com.example.myapplication.R.id.passwordet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class sign_in extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText emailEditText, passwordEditText;
    Button CreatAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();
        emailEditText = findViewById(R.id.emailet);
        passwordEditText = findViewById(R.id.passwordet); // Make sure you have an EditText with id 'password' in your XML layout
        MaterialButton signInButton = findViewById(R.id.loginButton); // Make sure you have a Button with id 'signInButton' in your XML layout
        CreatAccount = findViewById(R.id.create_button);

        CreatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_in.this, studentregistration.class);
                startActivity(intent);
            }
        });



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });
    }

    private void signInUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Check if fields are not empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Sign in the user with email and password
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in success, update UI with the signed-in user's information
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        // You can do additional actions after successful sign-in, such as navigating to another activity
                        // For now, let's just display a toast message
                        Toast.makeText(sign_in.this, "Sign-in successful", Toast.LENGTH_SHORT).show();

                        // Example: Navigate to another activity
                        startActivity(new Intent(sign_in.this, results.class));
                        finish(); // Close the current activity to prevent going back to sign-in screen
                    } else {
                        // If sign-in fails, display a message to the user
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidUserException e) {
                            // Handle invalid user exception (e.g., user not found)
                            Toast.makeText(sign_in.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            // Handle invalid credentials exception (e.g., wrong password)
                            Toast.makeText(sign_in.this, "Invalid password", Toast.LENGTH_SHORT).show();
                        } catch (FirebaseAuthException e) {
                            // Handle other FirebaseAuthExceptions
                            Toast.makeText(sign_in.this, "Sign-in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            // Handle other exceptions
                            Toast.makeText(sign_in.this, "Sign-in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}