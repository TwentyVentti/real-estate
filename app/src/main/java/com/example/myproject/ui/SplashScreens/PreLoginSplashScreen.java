package com.example.myproject.ui.SplashScreens;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.VideoView;

import com.example.myproject.R;
import com.example.myproject.ViewModels.loginActivity;

public class PreLoginSplashScreen extends Activity {
    VideoView videoView;
    View placeholder;

    //    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoView = (VideoView) findViewById(R.id.surfaceView);

        try {
            VideoView splashView = new VideoView(this);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pre_login);
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

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(PreLoginSplashScreen.this, loginActivity.class));
        finish();
    }


}