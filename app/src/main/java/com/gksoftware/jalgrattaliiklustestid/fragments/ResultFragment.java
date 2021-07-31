package com.gksoftware.jalgrattaliiklustestid.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gksoftware.jalgrattaliiklustestid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ResultFragment extends Fragment {

    private ListView resultContainer;
    private LinearLayout progressCircularContainer;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_results, container, false);

        resultContainer = v.findViewById(R.id.result_container);
        progressCircularContainer = v.findViewById(R.id.progress_circular_container);
        progressBar = v.findViewById(R.id.progress_circular);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Tulemused");

        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadResults();
        return v;
    }

    public void loadResults() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        db.collection("users")
                .document(currentUser.getUid())
                .collection("results")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {
                            ArrayList<String> resultList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                resultList.add("Punktid" + " : " + document.get("score").toString() + "\n" + "Protsent" + " : " + document.get("percentage").toString() + "\n" + "Küsimuste arv" + " : " + document.get("question_count").toString());

                            }
                            ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.list_item, resultList);
                            resultContainer.setAdapter(adapter);
                            progressCircularContainer.removeView(progressBar);
                        }
                    }
                });

    }
}
