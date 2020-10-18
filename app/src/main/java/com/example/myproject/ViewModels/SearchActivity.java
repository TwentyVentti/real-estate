package com.example.myproject.ViewModels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.Models.Parsing.BaseExp;
import com.example.myproject.Models.Parsing.Parser;
import com.example.myproject.Models.Parsing.Tokenizer;
import com.example.myproject.Models.UserDetails;
import com.example.myproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class SearchActivity extends AppCompatActivity {
    PopupWindow popUp;
    boolean click = true;
    FirebaseUser user;
    DatabaseReference ref;
    private String ID;


    EditText inputText;

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
        setContentView(R.layout.activity_search);
        inputText = (EditText) findViewById(R.id.citySelectEdit);

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users");
        try {
            ID = user.getUid();
        }
        catch (NullPointerException e){
            ID = "Guest";
        }
        final TextView userText = (TextView)findViewById(R.id.user);
        int id = 0;
        Intent intent = getIntent();
        if (intent != null){
            id = intent.getIntExtra("but",0);
        }


        final int finalId = id;
        ref.child(ID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserDetails profile = dataSnapshot.getValue(UserDetails.class);

                if (profile!=null & (finalId == 0)){
                    System.out.println(profile);
                    String name = profile.name;
                    userText.setText(name + "!");
                }
                else{
                    userText.setText("Guest!");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(SearchActivity.this,"Something went wrong!!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void searchClicked(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("UD",inputText.getText().toString());

        startActivity(intent);
    }
    public void onButtonShowPopupWindowClick(View view) {
        Resources RESOURCES = this.getResources();
        Object SYSTEM_SERVICE = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PopupWindow POPUP = new PopupWindow(this);
        ShowPopup("For the moment only these options are available --", RESOURCES,SYSTEM_SERVICE, POPUP);
    }
    public static PopupWindow POPUP_WINDOW_SCORE = null;

    public static void ShowPopup(String message, Resources resources, Object systemService,PopupWindow popUp )
    {
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) systemService;
        View layout = layoutInflater.inflate(R.layout.popup_window, null);

        // Creating the PopupWindow
        POPUP_WINDOW_SCORE = popUp;
        POPUP_WINDOW_SCORE.setContentView(layout);
        POPUP_WINDOW_SCORE.setWidth(width);
        POPUP_WINDOW_SCORE.setHeight(height);
        POPUP_WINDOW_SCORE.setFocusable(true);

        // prevent clickable background
        POPUP_WINDOW_SCORE.setBackgroundDrawable(null);

        POPUP_WINDOW_SCORE.showAtLocation(layout, Gravity.CENTER, 1, 1);

        TextView txtMessage = (TextView) layout.findViewById(R.id.layout_popup_txtMessage);
        txtMessage.setText(message);

        // Getting a reference to button one and do something
        Button butOne = (Button) layout.findViewById(R.id.layout_popup_butOne);
        butOne.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Do Something

                //Close Window
                POPUP_WINDOW_SCORE.dismiss();
            }
        });


    }
}
