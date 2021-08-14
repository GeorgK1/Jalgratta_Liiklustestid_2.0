package com.gksoftware.jalgrattaliiklustestid.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gksoftware.jalgrattaliiklustestid.R;

public class HomeFragment extends Fragment {
    private Button startSolvingButton;
    private Button chooseCategoryButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle(null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        startSolvingButton =  v.findViewById(R.id.start_solving_button);
        chooseCategoryButton = v.findViewById(R.id.choose_category_button);

        startSolvingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTestFragment();
            }
        });

        chooseCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChooseCategoryFragment();
            }
        });
        return v;

    }

    public void openTestFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TestPickerFragment()).addToBackStack(null).commit();
    }

    public void openChooseCategoryFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PickTestCategory()).addToBackStack(null).commit();
    }
}
