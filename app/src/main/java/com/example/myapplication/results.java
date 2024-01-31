package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class results extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextResults;
    private Button buttonUploadPDF;

    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        editTextName = findViewById(R.id.editTextName);
        editTextResults = findViewById(R.id.editTextResults);
        buttonUploadPDF = findViewById(R.id.buttonUploadPDF);

        buttonUploadPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String studentName = editTextName.getText().toString();
                String studentResults = editTextResults.getText().toString();


                Intent intent = new Intent(results.this, PdfUploadActivity.class);
                intent.putExtra("NAME", studentName);
                intent.putExtra("RESULTS", studentResults);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

        }
    }
}
