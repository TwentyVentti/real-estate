package com.example.myproject.ViewModels;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.myproject.R;

public class SplashScreenActivity extends Activity {
    VideoView videoView;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoView = (VideoView) findViewById(R.id.surfaceView);
        relativeLayout = findViewById(R.id.linLay);

        try {
            VideoView splashView = new VideoView(this);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.post_search);
            splashView.setVideoURI(video);
            setContentView(splashView);
            // Concepts from stack overflow
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float height = displayMetrics.heightPixels;
            float width = displayMetrics.widthPixels;
            float videoRatio = 1.6F;
            float screenRatio = height/width;
            System.out.println(splashView.getWidth());
            float scaleX = videoRatio / screenRatio;
            // Shift video downwards here for improvement instead of stretching/squishing
            if (scaleX >= 1f) {
                splashView.setScaleX(scaleX);
            } else {
                splashView.setScaleY(1f / scaleX);
            }
            splashView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            splashView.start();
        } catch (Exception ex) {
            jump();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(SplashScreenActivity.this, loginActivity.class ));
        finish();
    }
}