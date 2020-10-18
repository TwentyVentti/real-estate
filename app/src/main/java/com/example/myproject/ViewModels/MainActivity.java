package com.example.myproject.ViewModels;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.example.myproject.Models.Parsing.Parser;
import com.example.myproject.Models.Parsing.Token;
import com.example.myproject.Models.Parsing.Tokenizer;
import com.example.myproject.Models.User;
import com.example.myproject.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.utilities.Tree;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author Andrew Carse - u6666440
 */
public class MainActivity extends AppCompatActivity {
    public static String userDetails;
    public static User user = new User();
    TextView level;
    TextView days;


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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();
        userDetails = getIntent().getStringExtra("UD");


        getUserSelectionFromEdit();
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_home);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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


    public static User getUserSelectionFromEdit(){

        Tokenizer tokenizer = new Tokenizer(userDetails);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();

        System.out.println(t1.city);
        System.out.println(t1.country);
        System.out.println(t1.time);
        System.out.println(t1.tunit);
        System.out.println(t1.level);


        ArrayList<String> inferedSelection = new ArrayList<>();
        User userNow = new User();
        if (userDetails!=null){
            try{
//                ArrayList<String> editText = new ArrayList<>(Arrays.asList(userDetails.split(";")));
//                String country = editText.get(0).toUpperCase();
                // Assigning the country to the static user object
                // Adding the raw entries to the ArrayList if they match any countries we offer

                if (t1.country.equals("france")){
                    inferedSelection.add("French");
                    userNow.setCountry("France");
                    userNow.setLanguage("French");
                }
                else if (t1.country.equals("italy")){
                    inferedSelection.add("Italian");
                    userNow.setCountry("France");
                    userNow.setLanguage("Italian");
                }
                else if (t1.country.equals("netherlands")){
                    inferedSelection.add("Dutch");
                    userNow.setCountry("Netherlands");
                    userNow.setLanguage("Dutch");
                }
                else if (t1.country.equals("spain")){
                    inferedSelection.add("Spanish");
                    userNow.setCountry("Spain");
                    userNow.setLanguage("Spanish");
                }
                else {
                    System.out.println("No strings matched");
                }
                // Adding the city attribute
                userNow.setCity(t1.city);

                // Adding the city attribute to the arraylist
                inferedSelection.add(t1.city);
                int level = t1.level;

                userNow.setLevel(level);
                inferedSelection.add(Integer.toString(t1.level));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return userNow;
    }

}
