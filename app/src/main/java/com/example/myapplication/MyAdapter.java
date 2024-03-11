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

    private Map<String, Integer> gradePointMap; // Define gradePointMap

    public int calculateTotalPoints(int position) {
        // Get the data for the item at the given position
        getsubject subject = list.get(position);

        int totalPoints = 0;
        for (RadioButton radioButton : subject.getSelectedRadioButtons()) {
            String selectedGrade = radioButton.getText().toString();
            totalPoints += gradePointMap.get(selectedGrade);
        }

        return totalPoints;
    }

    public interface OnOptionSelectedListener {
        void onOptionSelected(int position, String selectedOption);
    }

    public MyAdapter(Context context, ArrayList<getsubject> list, OnOptionSelectedListener listener) {
        this.context = context;
        this.list = list;
        this.optionSelectedListener = listener;

        // Initialize gradePointMap (modify as per your grade-point mapping)
        gradePointMap = new HashMap<>();
        gradePointMap.put("A", 4);
        gradePointMap.put("B", 3);
        gradePointMap.put("C", 2);
        // Add other grade-point mappings as needed
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            subjects = itemView.findViewById(R.id.textViewSubject);
            RadioGroup radioGroup = itemView.findViewById(R.id.radioGroup);

            // Set up RadioGroup listener
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton selectedRadioButton = itemView.findViewById(checkedId);
                    if (selectedRadioButton != null) {
                        String selectedOption = selectedRadioButton.getText().toString();
                        // Notify the listener about the selected option
                        if (optionSelectedListener != null) {
                            optionSelectedListener.onOptionSelected(getAdapterPosition(), selectedOption);
                        }
                    }
                }
            });
        }
    }
}