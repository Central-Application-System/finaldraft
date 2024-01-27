package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class enter_results extends AppCompatActivity {
    String[]course = {"humanities","IT","engineering","Art and Design","business","Sciences","Communications/Media",
            ""};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> AdapterInstitutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_results);
        autoCompleteTextView = findViewById(R.id.questionet);

        AdapterInstitutions = new ArrayAdapter<String>(this,R.layout.list_institution,course);


        autoCompleteTextView.setAdapter(AdapterInstitutions);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item =parent.getItemAtPosition(position).toString();

            }
        });
    }
}