package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView italyImageView = (ImageView) findViewById(R.id.italyImageView);
        ImageView franceImageView = (ImageView) findViewById(R.id.franceImageView);
        italyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "You clicked on Italy!",
                        Toast.LENGTH_LONG).show();
            }
        });
        franceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "You clicked on France!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }



}