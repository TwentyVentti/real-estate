package com.example.myproject.ViewModels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.Models.BST.BinarySearch;
import com.example.myproject.Models.BST.Node;

import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailText;
    EditText passwordText;
    Button signin;
    Button guest;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView forgotpassword;
//    String LEVEL_1 = "Level 1";
//    String LEVEL_2 = "Level 2";
//    String LEVEL_3 = "Level 3";
//    String LEVEL_4 = "Level 4";
    public static final String a = "a";
//    public static HashMap<String,ArrayList<HashMap<String,ArrayList<Phrase>>>> phraseListHash;

    // Integer is Node.ID String is Section Name
    public static BiMap <Integer, String> IdAndSection = HashBiMap.create();
    public static HashMap <String, BinarySearch> levelBST;


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
//        try {
            levelBST = binaryFromJSON();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if (mAuth.getCurrentUser() != null) {
                finish();
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }


    }
    public void registerUser(View v) {
        Intent intent1 = new Intent(loginActivity.this, registrationActivity.class);
        startActivityForResult(intent1,1);

    }

    /**
     *
     *  Add objects to binary tree from sorted json obj
     *  The hashmap's string is a particular language and the binary search is the corresponding BST
     *  For each node we only need 3 parameters (Their IDs, english phrase, and language phrase)
     *  1. Make an ArrayList<Node> for each language and pass it in construct to create the BST
     *  (above is similar to Construct in BST)
     *  Time to constrct = n + logn
     *  Also store hashmap of data[it][0]["level"] to the string of sectionName
     * @return Hashmap of Language to the corresponding BSTs
     */

    public HashMap<String, BinarySearch> binaryFromJSON() {
        if (IdAndSection != null) {
            IdAndSection.clear();
        }
        HashMap <String, BinarySearch> LanguageToBST = new HashMap<>();
        try {
            HashMap <String, ArrayList<Node>> LanguageToDetails = new HashMap<>();
            JSONObject sectionObj = new JSONObject(loadJSON());
            JSONArray sectionNames = sectionObj.names();
            JSONArray sectionValues = sectionObj.toJSONArray(sectionNames);
            assert sectionNames != null;
            assert sectionValues != null;
            LanguageToDetails.put("French", new ArrayList<Node>());
            LanguageToDetails.put("Dutch", new ArrayList<Node>());
            LanguageToDetails.put("Spanish", new ArrayList<Node>());
            LanguageToDetails.put("Italian", new ArrayList<Node>());
            LanguageToDetails.put("German", new ArrayList<Node>());
            for (int i=0; i< sectionNames.length(); i++) {
                JSONArray levelObj = ((JSONArray) sectionValues.get(i));
                JSONObject idLevel = (JSONObject) levelObj.get(0);
                for (int j = 0; j < levelObj.length(); j++) {
                    JSONObject instance = (JSONObject) levelObj.get(j);
                    String language = instance.getString("language");
                    String englishPhrase = instance.getString("english");
                    String languagePhrase = instance.getString("phrase");
                    int id = (int) instance.get("id");
                    LanguageToDetails.get(language).add(new Node(id,englishPhrase,languagePhrase));
                }
                int keyId = (int) idLevel.get("id") / 1000;
                IdAndSection.put(keyId, sectionNames.get(i).toString());
            }
            for ( Map.Entry <String, ArrayList<Node>> imp : LanguageToDetails.entrySet()) {
                LanguageToBST.put(imp.getKey(), new BinarySearch(imp.getValue()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return LanguageToBST;
    }

//    public HashMap<String,ArrayList<HashMap<String,ArrayList<Phrase>>>>ObjectFromJSON() throws IOException{
//        HashMap<String,ArrayList<HashMap<String,ArrayList<Phrase>>>> outerHashMap = new HashMap<>();
//        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//
//            JSONArray outerNames = obj.names();
//
//            JSONArray outerValues = obj.toJSONArray(outerNames);
//            assert outerValues != null;
//            JSONObject innerObj = ((JSONObject) outerValues.get(0));
//            JSONArray innerNames = innerObj.names();
//            assert innerNames != null;
//            int level = 0;
//            ArrayList<HashMap<String,ArrayList<Phrase>>> innerArrayListHashMap = new ArrayList<>();
//            ArrayList<HashMap<String,ArrayList<Phrase>>> tempGet = new ArrayList<>();
//            HashMap<String,ArrayList<Phrase>> innerHashMap = new HashMap<>();
//            ArrayList<Phrase> innerArrayList = new ArrayList<>();
//
//            for (int i = 0; i < innerNames.length(); i++) {
//                JSONArray sectionArray = innerObj.getJSONArray((String) innerNames.get(i));
//                for (int j = 0; j < sectionArray.length(); j++) {
//                    JSONObject phraseObject = sectionArray.getJSONObject(j);
//                    String dutch = phraseObject.getString("dutch");
//                    String english = phraseObject.getString("english");
//                    String french = phraseObject.getString("french");
//                    String italian = phraseObject.getString("italian");
//                    String spanish = phraseObject.getString("spanish");
//                    String section = phraseObject.getString("section");
//                    int id = phraseObject.getInt("id");
//                    level = phraseObject.getInt("level");
//                    Phrase phrase = new Phrase(english,french,italian,spanish,dutch,section,level,id);
//                    innerArrayList.add(phrase);
////                    innerArrayListHashMap.add(phrase);
//                }
//                String sectionName = (String) innerNames.get(i);
//                innerHashMap.put(sectionName,innerArrayList);
//                innerArrayList = new ArrayList<>();
//                innerArrayListHashMap.add(innerHashMap);
//
//            switch (level){
//                case 1:
//                    if (outerHashMap.get(LEVEL_1) == null) {
//                        outerHashMap.put(LEVEL_1, innerArrayListHashMap);
//                    }
//                    else {
//                        Objects.requireNonNull(outerHashMap.get(LEVEL_1)).add(innerHashMap);
//                    }
//                    break;
//
//                case 2:
//                    if (outerHashMap.get(LEVEL_2) == null) {
//                        outerHashMap.put(LEVEL_2, innerArrayListHashMap);
//                    }
//                    else {
//                        Objects.requireNonNull(outerHashMap.get(LEVEL_2)).add(innerHashMap);
//                    }
//                    break;
//
//                case 3:
//                    if (outerHashMap.get(LEVEL_3) == null) {
//                        outerHashMap.put(LEVEL_3, innerArrayListHashMap);
//                    }
//                    else {
//                        Objects.requireNonNull(outerHashMap.get(LEVEL_3)).add(innerHashMap);
//                    }
//                    break;
//
//                case 4:
//                    if (outerHashMap.get(LEVEL_4) == null) {
//                        outerHashMap.put(LEVEL_4, innerArrayListHashMap);
//                    }
//                    else {
//                        outerHashMap.get(LEVEL_4).add(innerHashMap);
//                    }
//                    break;
//                default:
//                    break;
//            }
//                innerArrayListHashMap = new ArrayList<>();
//                innerHashMap = new HashMap<>();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return outerHashMap;
//    }

    public String loadJSON() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("temp.json");
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

//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = this.getAssets().open("phrase_array.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, StandardCharsets.UTF_8);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return json;
//    }

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
                    FirebaseUser user = mAuth.getCurrentUser();
                }
                else{
                    Toast.makeText(loginActivity.this,"Failed to login! Please check your credentials",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

}
