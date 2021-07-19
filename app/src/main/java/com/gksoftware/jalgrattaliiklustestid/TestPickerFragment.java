package com.gksoftware.jalgrattaliiklustestid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class TestPickerFragment extends Fragment {
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test_picker, container, false);
        radioGroup = v.findViewById(R.id.radio_group);
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = v.findViewById(radioId);

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkButton(v);
            }
        });

        Button confirmButton = v.findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("stuff");
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle("Testide menüü");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return v;
    }

    public void checkButton(View v) {

        Toast.makeText(getActivity(), "Valitud: " + radioButton.getText(), Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onDestroyView() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(null);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        super.onDestroyView();
    }
}
