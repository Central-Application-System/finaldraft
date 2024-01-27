package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hodregistration extends AppCompatActivity {
    TextInputEditText editTextemail,editTextpassword,editTextfullname,editTextusername;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hodregistration);

        editTextfullname = findViewById(R.id.fullnameet);
        editTextemail = findViewById(R.id.emailet);
        editTextpassword = findViewById(R.id.passwordet);
        editTextusername = findViewById(R.id.fullnameet);


}
    }