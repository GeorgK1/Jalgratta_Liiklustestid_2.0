package com.gksoftware.jalgrattaliiklustestid.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gksoftware.jalgrattaliiklustestid.R;
import com.gksoftware.jalgrattaliiklustestid.models.Question;
import com.gksoftware.jalgrattaliiklustestid.utils.QuizDbHelper;
import com.gksoftware.jalgrattaliiklustestid.viewmodels.SharedViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickTestFragment extends Fragment {
    private SharedViewModel viewModel;
    private TextView questionCountText;
    private TextView questionTitle;
    private TextView explanationText;
    private RadioGroup answerButtonGroup;
    private List<Question> questionList;
    private int questionCounter;
    private Question currentQuestion;
    private RadioButton answerButton;
    private List<String> optionList;
    private LinearLayout answerButtonContainer;
    private Button checkedRadioButton;
    private int checkedRadioButtonId;
    private String checkedRadioButtonText;
    private String[] splitSequence;
    private Button continueButton;
    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private int score;
    private boolean answered;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quick_test, container, false);
        questionCountText = v.findViewById(R.id.question_count_text);
        questionTitle = v.findViewById(R.id.question_title);
        answerButtonGroup = v.findViewById(R.id.answer_button_group);
        continueButton = v.findViewById(R.id.button_continue);
        answerButtonContainer = v.findViewById(R.id.answer_button_container);

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    currentUser = mAuth.getCurrentUser();
                } else {
                    Toast.makeText(getContext(), "Internetiühendusega on häired, testid töötavad ent tulemused ei salvestu", Toast.LENGTH_SHORT);
                }
            }
        });





        QuizDbHelper dbHelper = new QuizDbHelper(getContext());
        questionList = dbHelper.getAllQuestions();
        explanationText = v.findViewById(R.id.explanation_test);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    answerQuestion();
                } else {
                    loadQuestions();
                }

            }
        });

        answerButtonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedRadioButtonId = answerButtonGroup.getCheckedRadioButtonId();
                checkedRadioButton = v.findViewById(checkedRadioButtonId);
                checkedRadioButtonText = (String) checkedRadioButton.getText();

                continueButton.setEnabled(true);
            }
        });

        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Testiküsimused");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(getActivity()).get(SharedViewModel.class);
        viewModel.getQuestionCount().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
                splitSequence = charSequence.toString().split(" ");

                loadQuestions();
            }
        });

    }

    public void loadQuestions() {
        answered = false;


        if(questionCounter < Integer.parseInt(splitSequence[0])) {
            questionCountText.setText(questionCounter + 1 + "/" + splitSequence[0]);
            continueButton.setText("Vasta");
            continueButton.setEnabled(false);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            currentQuestion = questionList.get(questionCounter);
            questionTitle.setText(currentQuestion.getQuestion());
            answerButtonGroup.removeAllViews();
            explanationText.setText("");
            optionList = currentQuestion.getAllOptions();

            for (String option : optionList) {

                if (!option.equals("puudub")) {
                    answerButton = new RadioButton(getContext());
                    answerButton.setText(option);
                    answerButtonGroup.addView(answerButton);


                }

            }
        } else {
            finishTest();
        }


    }

    public void showSolution() {
        if (optionList.indexOf(checkedRadioButtonText) == currentQuestion.getRightAnswer()) {
            questionTitle.setText("Õige vastus!");
            score++;
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.right_answer_background)));
        } else {
            questionTitle.setText("Vale vastus!");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.wrong_answer_background)));
        }
        continueButton.setText("Järgmine küsimus");


        explanationText.setText(currentQuestion.getExplanation());
    }

    public void answerQuestion() {
        questionCounter++;
        answered = true;
        showSolution();
    }

    public void finishTest() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        Map<String, Object> userResults = new HashMap<>();
        userResults.put("score", score);
        userResults.put("percentage", Math.floor(score / Float.parseFloat(splitSequence[0]) * 100));
        userResults.put("question_count", splitSequence[0]);

        db.collection("users")
                .document(currentUser.getUid())
                .collection("results")
                .document()
                .set(userResults, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Tulemused edukalt salvestatud", Toast.LENGTH_SHORT);
                    }
                });


        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ResultFragment()).addToBackStack(null).commit();
    }
}
