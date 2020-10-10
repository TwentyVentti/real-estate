package com.example.myproject.ui.ArivingDest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArrivingDestViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ArrivingDestViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}