package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class sign_in extends AppCompatActivity {

    private Button btn;
    TextInputEditText editTextemail,editTextpassword;
Button login;
Button student;
FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        login= findViewById(R.id.loginButton);

        btn = (Button) findViewById(R.id.create_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_in.this, register.class);
                startActivity(intent);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email, password;
                        email = String.valueOf(editTextemail.getText());
                        password= String.valueOf(editTextpassword.getText());

                        if(TextUtils.isEmpty(email)){
                            Toast.makeText(sign_in.this,"Enter Email",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(TextUtils.isEmpty(password)){
                            Toast.makeText(sign_in.this,"Enter Password",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        firebaseAuth.signInWithEmailAndPassword(email,password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(sign_in.this,"login successful",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(sign_in.this,register.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(sign_in.this,"login unsuccessful",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });
            }
        }
        );

    }

}