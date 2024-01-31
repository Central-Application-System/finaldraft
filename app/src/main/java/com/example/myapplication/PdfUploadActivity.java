package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class PdfUploadActivity extends AppCompatActivity {
    private Button buttonUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_upload);
        buttonUpload = findViewById(R.id.buttonUpload);

        // Retrieve data passed from MainActivity
        Intent intent = getIntent();
        String studentName = intent.getStringExtra("NAME");
        String studentResults = intent.getStringExtra("RESULTS");

        // Use the data as needed, for example, display it in a TextView

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement PDF upload logic here

                // For the sake of example, show a toast indicating success
                Toast.makeText(PdfUploadActivity.this, "PDF Uploaded Successfully", Toast.LENGTH_SHORT).show();

                // Pass the result back to MainActivity if needed
                setResult(RESULT_OK);
                finish();
            }
        });


    }
}