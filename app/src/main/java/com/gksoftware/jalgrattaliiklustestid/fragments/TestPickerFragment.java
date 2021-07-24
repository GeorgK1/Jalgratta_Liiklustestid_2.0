package com.gksoftware.jalgrattaliiklustestid.fragments;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gksoftware.jalgrattaliiklustestid.R;
import com.gksoftware.jalgrattaliiklustestid.viewmodels.SharedViewModel;

public class TestPickerFragment extends Fragment {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private int radioId;
    private SharedViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_test_picker, container, false);
        radioGroup = v.findViewById(R.id.radio_group);
        radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = v.findViewById(radioId);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = v.findViewById(radioId);
                checkButton(v);
            }
        });

        Button confirmButton = v.findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToQuickTestFragment();
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Testide menüü");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return v;
    }

    public void checkButton(View v) {
        Toast.makeText(getActivity(), "Valitud: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        viewModel.getQuestionCount().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {

            }
        });
    }

    public void navigateToQuickTestFragment() {
        viewModel.setQuestionCount(radioButton.getText());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QuickTestFragment()).addToBackStack(null).commit();
    }
}
