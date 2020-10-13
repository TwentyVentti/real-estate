package com.example.myproject.ViewModels;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myproject.Models.Parsing.Token;
import com.example.myproject.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.core.utilities.Tree;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    public static String userDetails;

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setDrawerLayout(drawer)
                .build();
        userDetails = getIntent().getStringExtra("UD");
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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static ArrayList<String> getUserSelectionFromEdit(){
        ArrayList<String> inferedSelection = new ArrayList<>();
        if (userDetails!=null){
            try{

                ArrayList<String> editText = new ArrayList<>(Arrays.asList(userDetails.split(";")));
                String country = editText.get(0).toUpperCase();
                if (country.equals("FRANCE")){
                    inferedSelection.add("French");
                }
                else if (country.equals("ITALY")){
                    inferedSelection.add("Italian");
                }
                else if (country.equals("NETHERLANDS")){
                    inferedSelection.add("Dutch");
                }
                else if (country.equals("SPAIN")){
                    inferedSelection.add("Spanish");
                }
                inferedSelection.add(editText.get(1));
                inferedSelection.add(Integer.toString(levelFromDuration(editText.get(2))));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return inferedSelection;
    }

    public static int levelFromDuration(String duration){
        char firstChar = duration.charAt(0);
        String unitOfTime = "";
        int totalDays = 0;
        StringBuilder number = new StringBuilder();
        if (Character.isDigit(duration.charAt(0))) {
            number.append(firstChar);
            for (int i = 1; i < duration.length(); i++) {
                if (Character.isDigit(duration.charAt(i))) {
                    number.append(duration.charAt(i));
                } else {
                    unitOfTime = duration.substring(i).toUpperCase();
                }
            }
        }

        if (unitOfTime.equals("DAY")||unitOfTime.equals("DAYS")){
            totalDays = Integer.parseInt(number.toString());
        }
        else if (unitOfTime.equals("WEEK")||unitOfTime.equals("WEEKS")){
            totalDays = Integer.parseInt(number.toString())*7;
        }
        else if (unitOfTime.equals("MONTH")||unitOfTime.equals("MONTHS")){
            totalDays = Integer.parseInt(number.toString())*30;
        }
        int level = 0;
        if (totalDays<=7){
            level = 1;
        } else if (totalDays<=14){
            level = 2;
        } else if (totalDays<=30){
            level =3;
        }
        return level;
    }
}
