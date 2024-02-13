package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class business extends AppCompatActivity {
    String[] artCourses = {"Marketing" +
            " Accounting", "Public relations", "Economics"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        TextView MarketingTextView = findViewById(R.id.marketing);
        TextView Accounting = findViewById(R.id.Accounting);
        TextView Public_relations = findViewById(R.id.public_relation);
        TextView Economics = findViewById(R.id.econ);

        Economics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of business", "Economics");
            }
        });

        Public_relations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of business", "Public Relations");
            }
        });

        Accounting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of business", "Accounting");
            }
        });

        MarketingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInstitutions("Faculty of business", "Marketing");
            }
        });
    }

    private void navigateToInstitutions(String selectedFaculty, String selectedCourse) {
        Intent intent = new Intent(business.this, institutions.class);
        intent.putExtra("selectedFaculty", selectedFaculty);
        intent.putExtra("selectedCourse", selectedCourse);
        startActivity(intent);
    }
}



