package com.example.handicrafts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
//sdp library for supporting all screen size

public class splash extends AppCompatActivity {
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.logo);

        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim1);
        imageView.startAnimation(animation);

        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(splash.this,Home.class);
            startActivity(mainIntent);
            finish();
        },3000);




    }
}