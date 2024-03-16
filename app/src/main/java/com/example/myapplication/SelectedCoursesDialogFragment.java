package com.example.myapplication;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class SelectedCoursesDialogFragment extends DialogFragment {

    private ArrayList<String> selectedCourses;

    public SelectedCoursesDialogFragment(ArrayList<String> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Selected Courses");
        if (selectedCourses.isEmpty()) {
            builder.setMessage("No courses selected.");
        } else {
            StringBuilder message = new StringBuilder();
            for (String course : selectedCourses) {
                message.append(course).append("\n");
            }
            builder.setMessage(message.toString());
        }
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle confirm button click
                Toast.makeText(requireContext(), "Application confirmed!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}