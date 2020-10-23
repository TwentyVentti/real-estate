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
import android.widget.Toast;

import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * This class will help user to reset the password of their account by sending a link to their email.
 * @author Purvesh Mukesh Badmera - u7084724
 */
public class resetpasswordActivity extends AppCompatActivity {

    EditText email;
    Button reset;
    ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, loginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        email = findViewById(R.id.emailid);
        reset = findViewById(R.id.button2);
        progressBar = findViewById(R.id.progressBar4);
        auth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String e = email.getText().toString().trim();

        if(e.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(e).matches()){
            email.setError("Enter valid email address");
            email.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(e).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(resetpasswordActivity.this,"Check your mail to reset password!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(resetpasswordActivity.this,"Please try again!",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}