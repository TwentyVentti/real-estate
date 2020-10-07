package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class CitySelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityselect);
        ImageView italyImageView = (ImageView) findViewById(R.id.italyImageView);
        ImageView franceImageView = (ImageView) findViewById(R.id.franceImageView);
        italyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CitySelectActivity.this,
                        "You clicked on Italy!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CitySelectActivity.this,MainActivity.class);
                intent.putExtra("Language","Italian");
                startActivityForResult(intent,1);
            }
        });
        franceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CitySelectActivity.this,
                        "You clicked on France!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(CitySelectActivity.this,MainActivity.class);
                intent.putExtra("Language","French");
                startActivityForResult(intent,1);
            }
        });
    }



}