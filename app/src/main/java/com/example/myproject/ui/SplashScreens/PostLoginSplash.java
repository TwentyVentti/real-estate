package com.example.myproject.ui.SplashScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

import com.example.myproject.Models.Parsing.BaseExp;
import com.example.myproject.R;
import com.example.myproject.ViewModels.MainActivity;
import com.example.myproject.ViewModels.SearchActivity;

public class PostLoginSplash extends AppCompatActivity {
    VideoView videoView;
    View placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_search_splash);

        videoView = (VideoView) findViewById(R.id.surfaceView);

        try {
            VideoView splashView = new VideoView(this);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.post_search);
            setContentView(splashView);
            splashView.setVideoURI(video);


            splashView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoView = splashView;
            videoView.setZOrderOnTop(true);
            videoView.start();
        } catch (Exception ex) {
            jump();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }
//intent.putExtra("UD", t1);
    private void jump() {
        if (isFinishing())
            return;
        Intent intent =new Intent(PostLoginSplash.this, SearchActivity.class);
        Integer intExtra = getIntent().getIntExtra("but",0);
        intent.putExtra("UD",intExtra);
        startActivity(intent);
        finish();
    }

}