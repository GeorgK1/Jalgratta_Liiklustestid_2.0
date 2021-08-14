package com.gksoftware.jalgrattaliiklustestid.models;

import com.gksoftware.jalgrattaliiklustestid.R;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String category;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private int rightAnswer;
    private String explanation;


    public Question() {

    }

    public Question(String question, String option1, String option2, String option3, String option4, String option5, int rightAnswer, String category, String explanation) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.option5 = option5;
        this.rightAnswer = rightAnswer;
        this.category = category;
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getAllOptions() {
        List<String> listOfOptions = new ArrayList<>();

        listOfOptions.add(option1);
        listOfOptions.add(option2);
        listOfOptions.add(option3);
        listOfOptions.add(option4);
        listOfOptions.add(option5);

        return listOfOptions;
    }

    public static List<Integer> getAllCategories() {

        List<Integer> listOfCategories = new ArrayList<>();

        listOfCategories.add(R.string.category_1);
        listOfCategories.add(R.string.category_2);
        listOfCategories.add(R.string.category_3);
        listOfCategories.add(R.string.category_4);
        listOfCategories.add(R.string.category_5);
        listOfCategories.add(R.string.category_6);
        listOfCategories.add(R.string.category_7);
        listOfCategories.add(R.string.category_8);
        listOfCategories.add(R.string.category_9);
        listOfCategories.add(R.string.category_10);

        return listOfCategories;
    }
}
