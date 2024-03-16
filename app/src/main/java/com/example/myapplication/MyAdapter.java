package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap; // Add this import
import java.util.Map; // Add this import

import android.content.Context;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<getsubject> list;
    private OnOptionSelectedListener optionSelectedListener;
    private Map<String, Integer> gradePointMap;

    public interface OnOptionSelectedListener {
        void onOptionSelected(int position, String selectedOption);

    }

    public MyAdapter(Context context, ArrayList<getsubject> list, OnOptionSelectedListener listener) {
        this.context = context;
        this.list = list;
        this.optionSelectedListener = listener;

        // Initialize gradePointMap
        gradePointMap = new HashMap<>();
        gradePointMap.put("A", 8);
        gradePointMap.put("B", 7);
        gradePointMap.put("C", 6);
        gradePointMap.put("D", 5);
        gradePointMap.put("E", 4);
        // Add other grade-point mappings as needed
    }

    public int calculateTotalPoints() {
        int totalPoints = 0;
        for (getsubject subject : list) {
            for (RadioButton radioButton : subject.getSelectedRadioButtons()) {
                String selectedGrade = radioButton.getText().toString();
                // Add grade points to total points
                totalPoints += gradePointMap.get(selectedGrade);
            }
        }
        return totalPoints;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.subjectlayout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        getsubject subject = list.get(position);
        holder.subjects.setText(subject.getSubjects());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ViewHolder class within MyAdapter
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView subjects;
        RadioGroup radioGroup;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            subjects = itemView.findViewById(R.id.textViewSubject);
            radioGroup = itemView.findViewById(R.id.radioGroup);

            // Set up RadioGroup listener
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton selectedRadioButton = itemView.findViewById(checkedId);
                    if (selectedRadioButton != null) {
                        // Get the corresponding getsubject object
                        getsubject currentSubject = list.get(getAdapterPosition());

                        currentSubject.addSelectedRadioButton(selectedRadioButton);

                        String selectedOption = selectedRadioButton.getText().toString();

                        if (optionSelectedListener != null) {
                            optionSelectedListener.onOptionSelected(getAdapterPosition(), selectedOption);
                        }
                    }
                }
            });
        }
    }
}