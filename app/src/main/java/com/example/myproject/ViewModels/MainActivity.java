package com.example.myproject.ViewModels;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myproject.Models.Parsing.BaseExp;
import com.example.myproject.Models.SearchDetails;
import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;


/**
 * @author Andrew Carse - u6666440
 * @author Abhaas Goyol - u7145384
 */
public class MainActivity extends AppCompatActivity {
    public BaseExp userDetails;
    @SuppressLint("StaticFieldLeak")
    static TextView level;
    @SuppressLint("StaticFieldLeak")
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        level = header.findViewById(R.id.textView10);
        days = header.findViewById(R.id.textView9);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setOpenableLayout(drawer)
                .build();
        userDetails = (BaseExp) getIntent().getSerializableExtra("UD");

        assert userDetails != null;
        level.setText("Level: " + userDetails.level);
        days.setText("Duration: " + userDetails.time +" "+userDetails.tunit);

        // Replace with a better method name
        int id = 0;
        Intent intent = getIntent();
        if (intent != null){
            id = intent.getIntExtra("but",0);
        }
        if (id == 1) {
            surpriseFeature();
        }

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
        if (id == R.id.action_settings) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, loginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return  super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void surpriseFeature() {

        FirebaseUser Id;
        auth = FirebaseAuth.getInstance();
        String countryString = userDetails.country.trim();
        String cityString = userDetails.city.trim();
        String userString;
        Id = auth.getCurrentUser();

        userString = Id.getUid().trim();
        assert Id != null;

        SearchDetails search = new SearchDetails(userString, countryString, cityString);

        FirebaseDatabase.getInstance().getReference("SearchDetails")
                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .setValue(search).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
    }