package com.example.myproject.ui.HomePage;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class HomePageViewModel extends ViewModel {

    Random random = new Random();
    int LEVEL = random.nextInt(3);
    private MutableLiveData<String> mText;

    public HomePageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home ");
    }

    public LiveData<String> getText() {
        return mText;
    }



}