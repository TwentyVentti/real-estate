package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(SearchActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editText = (EditText)findViewById(R.id.citySelectEdit);



    }



    public void searchClicked(View v){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(editText.getText().toString(),1);
        startActivity(intent);
    }
}
