package com.gksoftware.jalgrattaliiklustestid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private Button start_solving_button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        start_solving_button = (Button) v.findViewById(R.id.start_solving_button);
        start_solving_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTestFragment();
            }
        });

        return v;

    }

    public void openTestFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TestPickerFragment()).addToBackStack(null).commit();
    }
}
