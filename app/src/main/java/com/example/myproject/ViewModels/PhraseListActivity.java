package com.example.myproject.ViewModels;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myproject.R;

public class PhraseListActivity extends AppCompatActivity {
    TextView sectionTextView;
    String section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase_list);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        };
        sectionTextView = findViewById(R.id.sectionTextView);
        Intent homePageIntent = getIntent();
        try {
            section = homePageIntent.getStringExtra("Section").replaceAll("_", " ");
        } catch (Exception ex){
            System.err.println("Null section title");
            ex.printStackTrace();
        }
        sectionTextView.setText(section);
    }

}