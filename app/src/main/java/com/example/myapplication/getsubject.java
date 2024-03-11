package com.example.myapplication;

import android.widget.RadioButton;

import java.util.ArrayList;

public class getsubject {
    private String subjects;
    private ArrayList<RadioButton> selectedRadioButtons; // Add this field

    public getsubject() {
        // Constructor
        selectedRadioButtons = new ArrayList<>();
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public ArrayList<RadioButton> getSelectedRadioButtons() {
        return selectedRadioButtons;
    }

    public void addSelectedRadioButton(RadioButton radioButton) {
        selectedRadioButtons.add(radioButton);
    }

    // Add other methods and fields as needed
}