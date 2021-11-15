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
import com.example.myproject.Models.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


/**
 * This class is reponsible for the registration of new user to the firebase.
 * @author Purvesh Mukesh Badmera - u7084724
 */
public class registrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name,age,email,password;
    private FirebaseAuth mAuth;
    private Button register;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mAuth = FirebaseAuth.getInstance();

        name = (EditText)findViewById(R.id.name);
        age = (EditText)findViewById(R.id.age);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        register = (Button)findViewById(R.id.registeruser);
        register.setOnClickListener(this);
        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registeruser:
                registerUser();
                break;
        }
    }

    /**
     * This function handles all the user details then validates it and sends it to the firebase.
     * @author Purvesh Mukesh Badmera - u7084724
     */
    private void registerUser() {
        final String e = email.getText().toString().trim();
        final String a = age.getText().toString().trim();
        final String n = name.getText().toString().trim();
        final String p = password.getText().toString().trim();

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

        if(a.isEmpty()){
            age.setError("Age is required");
            age.requestFocus();
            return;
        }

        if(n.isEmpty()){
            name.setError("Name is required");
            name.requestFocus();
            return;
        }

        if(p.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if(p.length() < 5){
            password.setError("Password should contain minimum 5 characters");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(e,p)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            UserDetails user = new UserDetails(n,a,e,p);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(registrationActivity.this, landlordLoginActivity.class);
                                        Toast.makeText(registrationActivity.this,"You have been registered succesfully",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        startActivityForResult(intent,1);
                                    }
                                    else{
                                        Toast.makeText(registrationActivity.this,"Failed to Register, Try again!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }
                        else{
                            Toast.makeText(registrationActivity.this,"Email ID already in use. Please use different one!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }



}