package com.example.myproject.ViewModels;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
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