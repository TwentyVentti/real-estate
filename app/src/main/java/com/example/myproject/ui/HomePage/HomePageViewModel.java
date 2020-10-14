package com.example.myproject.ui.HomePage;

import android.content.Intent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myproject.ViewModels.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.example.myproject.ViewModels.MainActivity.getUserSelectionFromEdit;
import static com.example.myproject.ViewModels.MainActivity.userDetails;

/**
 * TODO: Docstring
 * @author Andrew Carse - u6666440
 */
public class HomePageViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private TextView testTextView;

    public HomePageViewModel() {
        mText = new MutableLiveData<>();
        Intent intent =new Intent();
    }

    public LiveData<String> getText() {
        return mText;
    }



}