package com.vingcoz.dentalapp.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.vingcoz.dentalapp.HomeActivity;
import com.vingcoz.dentalapp.R;

import java.util.Objects;


public class SplashScreen extends AppCompatActivity {

    SharedPreferences MainPreference;
    boolean FirstLaunch;
    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        imageView=(ImageView)findViewById(R.id.image);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);

        imageView.startAnimation(animation);
        MainPreference = getSharedPreferences("MyPreference", MODE_PRIVATE);
        FirstLaunch = MainPreference.getBoolean("SplashScreen", false);
        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(this::LoadNextScreen, SPLASH_TIME_OUT);

    }

    private void LoadNextScreen() {

        if (!FirstLaunch) {
            startActivity(new Intent(SplashScreen.this, HomeActivity.class));
        } else {
            startActivity(new Intent(SplashScreen.this, Intro.class));
        }
        finish();
    }

}
