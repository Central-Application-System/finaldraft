package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class sign_in extends AppCompatActivity {

    private Button btn;
    EditText loginUsername,loginpassword;
    TextView register;
Button login;
Button student;
FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btn = (Button) findViewById(R.id.create_button);
        loginUsername = findViewById(R.id.name);
        loginpassword = findViewById(R.id.password);

        login = findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateUsername() && !validatePassword()){
                }else {
                    checkUser();
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_in.this, studentregistration.class);
                startActivity(intent);

            }
        }
        );

    }

    public boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Username cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String val = loginpassword.getText().toString();
        if (val.isEmpty()) {
            loginpassword.setError("Password cannot be empty");
            return false;
        } else {
            loginpassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String uusername =loginUsername.getText().toString().trim();
        String userpassword = loginpassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(uusername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    loginUsername.setError(null);

                    String passwordfrdb = snapshot.child(uusername).child("password").getValue(String.class);

                    if(passwordfrdb.equals(userpassword)){
                        loginUsername.setError(null);
                        Intent intent = new Intent(sign_in.this, results.class);
                        startActivity(intent);
                    }else {
                        loginpassword.setError("invalid credentials");
                        loginpassword.requestFocus();
                    }
                }else {
                    loginUsername.setError("user does not exist");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}