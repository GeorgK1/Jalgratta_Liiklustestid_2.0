package com.gksoftware.jalgrattaliiklustestid.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<CharSequence> questionCount = new MutableLiveData<>();

    public void setQuestionCount(CharSequence input) {
        questionCount.setValue(input);
    }

    public LiveData<CharSequence> getQuestionCount() {
        return questionCount;
    }
}
