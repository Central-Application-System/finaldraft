package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class hoddetails extends AppCompatActivity {
    String[]institutions = {"Eswatini Collage Of Technology","Southern Africa Nazarine University"
    ,"Christain University","William Pitcher Collage","Limkokwing University","University Of Eswatini"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String>AdapterInstitutions;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoddetails);

        register = findViewById(R.id.hoddetails);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(hoddetails.this, "registration complete please logon to the website", Toast.LENGTH_LONG).show();
            }
        });

        autoCompleteTextView = findViewById(R.id.auto_complete);

        AdapterInstitutions = new ArrayAdapter<String>(this,R.layout.list_institution,institutions);


        autoCompleteTextView.setAdapter(AdapterInstitutions);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item =parent.getItemAtPosition(position).toString();

            }
        });

  }
}
