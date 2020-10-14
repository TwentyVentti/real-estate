package com.example.myproject.ViewModels;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myproject.Models.Phrase;
import com.example.myproject.R;
import com.example.myproject.ui.HomePage.HomePageFragment;

import java.util.ArrayList;

public class PhraseListActivity extends AppCompatActivity {
    TextView sectionTextView;
    String section;
    ListView phraseListView;
    ArrayAdapter<String> phraseArrayAdapter;

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
        phraseListView = findViewById(R.id.phraseListView);
        sectionTextView = findViewById(R.id.sectionTextView);
//        phraseArrayAdapter = new ArrayAdapter<>(PhraseListActivity.this,android.R.layout.simple_list_item_1, sections);
        Intent homePageIntent = getIntent();
        try {
            section = homePageIntent.getStringExtra("Section").replaceAll("_", " ");
        } catch (Exception ex){
            System.err.println("Null section title");
            ex.printStackTrace();
        }
        sectionTextView.setText(section);
    }

    private ArrayList<String> getPhrases(){
        ArrayList<Phrase> phrases = new ArrayList<>();
        String currentSection = getIntent().getStringExtra("Section");
        for (int i = 0; i < HomePageFragment.levelArrayMap.size(); i++) {
            if (HomePageFragment.levelArrayMap.get(i).keySet().iterator().hasNext()){
                if (HomePageFragment.levelArrayMap.get(i).keySet().iterator().next().equals(currentSection)){
                    phrases=HomePageFragment.levelArrayMap.get(i).get(currentSection);
                }
            }
        }

        return new ArrayList<>();
    }

}