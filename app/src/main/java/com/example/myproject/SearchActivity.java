package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(SearchActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



    }

    public void citySelectClicked(View v){
        Intent intent = new Intent(SearchActivity.this,MainActivity.class);
        startActivity(intent);
    }



}
