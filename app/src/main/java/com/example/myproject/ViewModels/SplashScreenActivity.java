package com.example.myproject.ViewModels;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.myproject.R;

import static android.content.ContentValues.TAG;

public class SplashScreenActivity extends Activity {
    VideoView videoView;
    View placeholder;

    //    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoView = (VideoView) findViewById(R.id.surfaceView);

        try {
            VideoView splashView = new VideoView(this);
            setContentView(splashView);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_login_wider);
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

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(SplashScreenActivity.this, loginActivity.class));
        finish();
    }


}