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

import java.util.List;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quick_test, container, false);
        questionCountText = v.findViewById(R.id.question_count_text);
        questionTitle = v.findViewById(R.id.question_title);
        answerButtonGroup = v.findViewById(R.id.answer_button_group);
        continueButton = v.findViewById(R.id.button_continue);
        answerButtonContainer = v.findViewById(R.id.answer_button_container);
        QuizDbHelper dbHelper = new QuizDbHelper(getContext());
        questionList = dbHelper.getAllQuestions();
        explanationText = v.findViewById(R.id.explanation_test);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerQuestion();
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


            }
        });
        loadQuestions();
    }

    public void loadQuestions() {
       // questionCountText.setText(questionCounter + 1 + "/" + splitSequence[0]);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        currentQuestion = questionList.get(questionCounter);
        questionTitle.setText(currentQuestion.getQuestion());
        answerButtonGroup.removeAllViews();
        explanationText.setText("");
        optionList = currentQuestion.getAllOptions();

        for (String option :
                optionList) {

            if (!option.equals("puudub")) {
                answerButton = new RadioButton(getContext());
                answerButton.setText(option);
                answerButtonGroup.addView(answerButton);


            }

        }


    }

    public void answerQuestion() {
        questionCounter++;

        if (optionList.indexOf(checkedRadioButtonText) == currentQuestion.getRightAnswer()) {
            questionTitle.setText("Õige vastus!");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.right_answer_background)));
        } else {
            questionTitle.setText("Vale vastus!");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.wrong_answer_background)));
        }
        continueButton.setText("Järgmine küsimus");

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuestions();
            }
        });
        explanationText.setText(currentQuestion.getExplanation());
    }
}
