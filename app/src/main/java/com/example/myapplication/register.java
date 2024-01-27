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

    Button hod;



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




         hod=findViewById(R.id.hodButton);

         hod.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(register.this, hodregistration.class);
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