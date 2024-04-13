package com.example.handicrafts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.handicrafts.login.SignupPage;
//sdp library for supporting all screen size

public class splash extends AppCompatActivity {
    ImageView imageView;
    Boolean LOGGEDIN=false;
   // int userid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       imageView=findViewById(R.id.logo);

        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim1);
        imageView.startAnimation(animation);


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1); // -1 is the default value if userId is not found







        if(userId!=-1){
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {

                    Intent mainIntent = new Intent(splash.this, Home.class);
                    splash.this.startActivity(mainIntent);
                    splash.this.finish();
                }
            },3000);

        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i=new Intent(splash.this,SignupPage.class);
                    splash.this.startActivity(i);
                    splash.this.finish();
                }
            },2000);
        }




    }
}