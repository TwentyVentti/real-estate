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
import java.util.ArrayList;
import java.util.Collections;
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
        emailText = (EditText)findViewById(R.id.emailText);
        passwordText = (EditText)findViewById(R.id.passwordText);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        signin = (Button)findViewById(R.id.login_button);
        signin.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        forgotpassword = (TextView)findViewById(R.id.textView);
        forgotpassword.setOnClickListener(this);
        guest = (Button)findViewById(R.id.guest_user_button);


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
            JSONArray innerValues = innerObj.toJSONArray(innerNames);
//            JSONArray sections = (JSONObject) values.getJSONObject(0).toJSONArray();
//            Object town = sections.getString("Around_Town");
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
        phraseListHash = phraseListHashMap;
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
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void guestSessionClicked(View v) throws IOException {
        Intent intent = new Intent(loginActivity.this, SearchActivity.class);
        System.out.println("JSON ARRAY PHRASES:");
        ArrayList<HashMap<Integer,Phrase>> phraseHash = ObjectFromJSON();
        for (int i = 0; i <2; i++) {
            HashMap<Integer,Phrase> hashMap = phraseHash.get(i);
        }

        intent.putExtra("but",1);
        startActivityForResult(intent,1);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Phrase> getFromJson() throws IOException {
        Gson gson = new Gson();
        JsonReader jsonReader= null;

        final Type CUS_LIST_TYPE = new TypeToken<List<Phrase>>() {}.getType();
        File f = new File(getCacheDir()+"/phrase_array.json");
        FileOutputStream fos;
        if (!f.exists()) try {

            InputStream is = getAssets().open("phrase_array.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();


            fos = new FileOutputStream(f);
            fos.write(buffer);
            fos.close();
        } catch (Exception e) { throw new RuntimeException(e); }

        FileReader fileReader = new FileReader(f.getName());

        try{
            jsonReader = new JsonReader(fileReader);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        List<Phrase> x = gson.fromJson(String.valueOf(jsonReader),CUS_LIST_TYPE);
        for (Phrase p: x) {
            System.out.println(p.toString());
        }

        return gson.fromJson(String.valueOf(jsonReader),CUS_LIST_TYPE);
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
