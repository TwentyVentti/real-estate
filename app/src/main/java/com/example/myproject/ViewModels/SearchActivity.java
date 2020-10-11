package com.example.myproject.ViewModels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myproject.R;

public class SearchActivity extends AppCompatActivity {

    EditText inputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        inputText = (EditText) findViewById(R.id.citySelectEdit);

    }

    public void searchClicked(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("String",inputText.getText());
        startActivity(intent);
    }
}
