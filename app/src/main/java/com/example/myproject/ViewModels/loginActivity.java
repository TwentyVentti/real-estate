package com.example.myproject.ViewModels;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.Models.Phrase;
import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailText;
    EditText passwordText;
    Button register,signin,guest;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView forgotpassword;
    public static final String a = "a";
    public static ArrayList<HashMap<Integer,Phrase>> phraseListHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        progressBar = findViewById(R.id.progressBar);
        signin = findViewById(R.id.login_button);
        signin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        forgotpassword = findViewById(R.id.textView);
        forgotpassword.setOnClickListener(this);
        guest = findViewById(R.id.guest_user_button);
        try {
            phraseListHash = ObjectFromJSON();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registerUser(View v) {
        Intent intent1 = new Intent(loginActivity.this, registrationActivity.class);
        startActivityForResult(intent1,1);

    }
    public ArrayList<HashMap<Integer,Phrase>> ObjectFromJSON() throws IOException{
        ArrayList<HashMap<Integer, Phrase>> phraseListHashMap = new ArrayList<HashMap<Integer, Phrase>>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray outerNames = obj.names();
            JSONArray outerValues = obj.toJSONArray(outerNames);
            assert outerValues != null;
            JSONObject innerObj = ((JSONObject) outerValues.get(0));
            JSONArray innerNames = innerObj.names();
            assert innerNames != null;
            for (int i = 0; i < innerNames.length(); i++) {
                JSONArray sectionArray = innerObj.getJSONArray((String) innerNames.get(i));
                HashMap<Integer, Phrase> sectionHash = new HashMap<Integer, Phrase>();
                for (int j = 0; j < sectionArray.length(); j++) {
                    JSONObject phraseObject = sectionArray.getJSONObject(j);
                    String dutch = phraseObject.getString("dutch");
                    String english = phraseObject.getString("english");
                    String french = phraseObject.getString("french");
                    String italian = phraseObject.getString("italian");
                    String spanish = phraseObject.getString("spanish");
                    String section = phraseObject.getString("section");
                    int id = phraseObject.getInt("id");
                    int level = phraseObject.getInt("level");
                    Phrase phrase = new Phrase(english,french,italian,spanish,dutch,section,level,id);
                    sectionHash.put(j,phrase);
                }
                phraseListHashMap.add(sectionHash);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return phraseListHashMap;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("phrase_array.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void guestSessionClicked(View v) throws IOException {
        Intent intent = new Intent(loginActivity.this, SearchActivity.class);
        intent.putExtra("but",1);
        startActivityForResult(intent,1);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button:
                userLogin();
                break;

            case R.id.textView:
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
