package com.example.viewmodel29032022;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> textLiveData = new MutableLiveData<>();

    public LiveData<String> getText() {
        return textLiveData;
    }

    public void updateText(String text) {
        textLiveData.setValue(text);
    }
}
