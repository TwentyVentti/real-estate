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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        String editTextSearch = getIntent().getStringExtra("UserDetails");
        userDetails = editTextSearch;
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
        ArrayList<String> editText = new ArrayList<>(Arrays.asList(userDetails.split(";")));
        ArrayList<String> inferedSelection = new ArrayList<>();
        String country = editText.get(0);
        switch (country.toUpperCase()){
            case "FRANCE":
                inferedSelection.add("French");
            case "ITALY":
                inferedSelection.add("Italian");
            case "NETHERLANDS":
                inferedSelection.add("Dutch");
            case "SPAIN":
                inferedSelection.add("Spanish");
        }
        inferedSelection.add(editText.get(1));



        return inferedSelection;
    }
}
