package com.example.myproject.ui.HomePage;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.example.myproject.ViewModels.MainActivity.userDetails;

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