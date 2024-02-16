package com.example.handicrafts.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.handicrafts.Home;
import com.example.handicrafts.R;

public class LoginPage extends AppCompatActivity {
    ImageView backToHome,gotoSignUp;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        init();

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this, Home.class));
            }
        });
        gotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this, SignupPage.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginPage.this,"Login Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(){
        backToHome = findViewById(R.id.img_backtoHome);
        gotoSignUp = findViewById(R.id.img_gotoSignUp);
        login = findViewById(R.id.btn_login);

    }
}