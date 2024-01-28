package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class studentregistration extends AppCompatActivity {

    EditText signupemail,signuppassword,signupfullname,signupusername;
    //TextView signinRedirectText;
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
        signupusername = findViewById(R.id.cpasswordet);

        result=findViewById(R.id.resultet);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database= FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String password = signuppassword.getText().toString();
                String fullname = signupfullname.getText().toString();
                String email = signupemail.getText().toString();
                String username = signupusername.getText().toString();

                helperClass helperClass = new helperClass(fullname , password , username , email);
                reference.child(username).setValue(helperClass);

                Toast.makeText(studentregistration.this,"signup successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( studentregistration.this, enter_results.class);
                startActivity(intent);
            }
        });


}
    }