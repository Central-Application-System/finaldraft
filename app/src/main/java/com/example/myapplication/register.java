package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
    private Button btn,button;
    TextInputEditText editTextemail,editTextpassword;
    Button hod;
    FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn = (Button) findViewById(R.id.hodButton);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       Intent intent = new Intent(register.this, hodregistration.class);
                                       startActivity(intent);
                                   }
                               }
        );


        editTextemail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.password);

         hod=findViewById(R.id.hodButton);

         hod.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(register.this, hoddetails.class);
                 startActivity(intent);
                 finish();
             }
         });


    }

    public void StudentClick(View view) {

        Intent intent = new Intent(register.this, studentregistration.class);
                                    startActivity(intent);
    }




}