package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class loginActivity extends AppCompatActivity {
    EditText usernameText;
    EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameText = (EditText)findViewById(R.id.userNameText);
        passwordText = (EditText)findViewById(R.id.passwordText);
    }

    public void guestSessionClicked(View v){
        Intent intent = new Intent(loginActivity.this,MainActivity.class);
        startActivityForResult(intent,1);
    }
    public void goLoginClicked(View v){
        Intent intent = new Intent(loginActivity.this, MainActivity.class);
        if (readAssets()){
            startActivityForResult(intent,1);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Not a user, please create account", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public boolean readAssets(){
        User user = new User("",usernameText.getText().toString(),passwordText.getText().toString());
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("loginDetails.csv"), "UTF-8"));
            String line;
            while ((line=reader.readLine())!=null){
                String[] tokens = line.split(",");
                for (String token : tokens){
                    if (((user.getUsername().equals(token) )||(user.getPassword().equals(token) ))){
                        return true;
                    }
                }
            }
        } catch (IOException ex){
            Toast toast = Toast.makeText(getApplicationContext(), "Not a user, please create account", Toast.LENGTH_SHORT);
            toast.show();
        }
        return false;
    }


}