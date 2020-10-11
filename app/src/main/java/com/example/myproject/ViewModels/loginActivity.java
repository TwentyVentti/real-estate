package com.example.myproject.ViewModels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailText;
    EditText passwordText;
    Button register,signin;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText = (EditText)findViewById(R.id.emailText);
        passwordText = (EditText)findViewById(R.id.passwordText);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        signin = (Button)findViewById(R.id.login_button);
        signin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        forgotpassword = (TextView)findViewById(R.id.emailText);
        forgotpassword.setOnClickListener(this);

    }

    public void registerUser(View v) {
        Intent intent1 = new Intent(loginActivity.this, registrationActivity.class);
        startActivityForResult(intent1,1);
    }

    public void guestSessionClicked(View v){
        Intent intent = new Intent(loginActivity.this, SearchActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                userLogin();
                break;

            case R.id.emailText:
                startActivity(new Intent(this,resetpasswordActivity.class));
                break;
        }
        
    }

    private void userLogin() {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(email.isEmpty()){
            emailText.setError("Enter a email address");
            emailText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Enter valid email address");
            emailText.requestFocus();
            return;
        }

        if(password.isEmpty()){
            passwordText.setError("Please enter your password");
            passwordText.requestFocus();
            return;
        }

        if(password.length() < 6){
            passwordText.setError("Password should contain minimum 6 characters");
            passwordText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(loginActivity.this, SearchActivity.class));
                }
                else{
                    Toast.makeText(loginActivity.this,"Failed to login! Please check your credentials",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }





}