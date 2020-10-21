package com.example.myproject.ViewModels;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myproject.Models.Parsing.BaseExp;
import com.example.myproject.Models.Parsing.GrammarException;
import com.example.myproject.Models.Parsing.Parser;
import com.example.myproject.Models.Parsing.ParserException;
import com.example.myproject.Models.Parsing.Token;
import com.example.myproject.Models.Parsing.TokenException;
import com.example.myproject.Models.Parsing.Tokenizer;
import com.example.myproject.Models.User;
import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.utilities.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;


/**
 * @author Andrew Carse - u6666440
 * @author Abhaas Goyol - u7145384
 */
public class MainActivity extends AppCompatActivity {
    public BaseExp userDetails;
    public static User user = new User();
    static TextView level;
    static TextView days;
    FirebaseAuth auth;


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        level = (TextView) header.findViewById(R.id.textView10);
        days = (TextView) header.findViewById(R.id.textView9);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();
        userDetails = (BaseExp) getIntent().getSerializableExtra("UD");

        level.setText("Level: " + userDetails.level);
        days.setText("Duration: " + userDetails.time +" "+userDetails.tunit);
//        try {
//            getUserSelectionFromEdit();
//        }
//        catch (GrammarException e) {
//            Intent intent = new Intent(this,SearchActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//            finish();
//            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
//        }
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_home);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent (this,loginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;


        }
        return  super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

        /*
    public class User {
        private String language;
        private Integer level;
        private String city;
        private Integer duration;
        private String country;
     */
//        public static User getUserSelectionFromEdit() throws GrammarException {
//            if (userDetails == null) {
//                throw new TokenException("NULL");
//            }
//            Tokenizer tokenizer = new Tokenizer(userDetails);
//            BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
//            t1.evaluate();
//            level.setText("Level: " + t1.level);
//            days.setText("Duration: " + t1.time +" "+t1.tunit);
//
//
//            ArrayList<String> inferedSelection = new ArrayList<>();
//            User userNow = new User();
//
//            HashMap<String, String> language = new HashMap<>();
//            language.put("france", "French");
//            language.put("italy", "Italian");
//            language.put("netherlands", "Dutch");
//            language.put("spain", "Spanish");
//            language.put("germany","German");
//
//                if (t1.country == null) {
//                    throw new TokenException("CM");
//                }
//                if (!language.containsKey(t1.country)) {
//                    throw new TokenException("ICO");
//                }
//                userNow.setCountry(t1.country.substring(0, 1).toUpperCase() + t1.country.substring(1));
//                userNow.setLanguage(language.get(t1.country));
//
//                // TODO: Improve Assignment of city by taking the value of the capital from the db
//                t1.city = t1.city == null ? "Paris" : t1.city;
//
//                userNow.setCity(t1.city);
//                inferedSelection.add(t1.city);
//
//                t1.level = t1.level == 0 ? 1: t1.level;
//                int level = t1.level;
//                userNow.setLevel(level);
//                inferedSelection.add(Integer.toString(t1.level));
//            return userNow;
//            }
//
//    auth = FirebaseAuth.getInstance();
//    String co = t1.country.trim();
//    String ci = t1.city.trim();
//    String u = auth.getCurrentUser().getUid().trim();
//
//    SearchDetails search = new SearchDetails(u,co,ci);
//
//        FirebaseDatabase.getInstance().getReference("SearchDetails")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//            .setValue(search).addOnCompleteListener(new OnCompleteListener<Void>() {
//        @Override
//        public void onComplete(@NonNull Task<Void> task) {
//
//        }
    }