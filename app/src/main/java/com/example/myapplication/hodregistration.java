package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hodregistration extends AppCompatActivity {
    EditText signupemail,signuppassword,signupfullname,signupusername;
    //TextView signinRedirectText;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodregistration);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

       signupfullname = findViewById(R.id.fullnameet);
        signupemail = findViewById(R.id.emailet);
        signuppassword = findViewById(R.id.passwordet);
        signupusername = findViewById(R.id.cpasswordet);

       register = findViewById(R.id.buttonet);

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
                           Toast.makeText(hodregistration.this, "Registration successful", Toast.LENGTH_SHORT).show();

                           // Start a new activity
                           Intent intent = new Intent(hodregistration.this,hoddetails.class);
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

//           }    signinRedirectText.setOnClickListener(new View.OnClickListener() {
//                   @Override
//                   public void onClick(View v) {
//                       Intent intent = new Intent( hodregistration.this, sign_in.class);
//                       startActivity(intent);
////
////                   }
////               });
//
}
}
