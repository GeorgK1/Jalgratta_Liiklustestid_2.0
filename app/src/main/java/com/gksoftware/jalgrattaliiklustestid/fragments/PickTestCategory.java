package com.gksoftware.jalgrattaliiklustestid.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gksoftware.jalgrattaliiklustestid.R;
import com.gksoftware.jalgrattaliiklustestid.models.Question;

import java.util.ArrayList;
import java.util.List;

public class PickTestCategory extends Fragment {
    private Button startButton;
    private Spinner categorySpinner;
    private List<String> categoriesList = new ArrayList<>();

    @Nullable
    @Override



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_solve_tests_by_category, container, false);

        startButton = v.findViewById(R.id.start_button);
        categorySpinner = v.findViewById(R.id.category_spinner);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TestByCategory()).addToBackStack(null).commit();
            }
        });

        for (Integer categoryInt : Question.getAllCategories()) {
            categoriesList.add(getResources().getString(categoryInt));
        }

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, categoriesList);
        categorySpinner.setAdapter(categoryAdapter);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Lahenda kategooriate kaupa");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return v;
    }


    };





