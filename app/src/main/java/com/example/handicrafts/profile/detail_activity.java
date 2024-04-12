package com.example.handicrafts.profile;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.handicrafts.R;
import com.google.android.material.button.MaterialButton;

public class detail_activity extends AppCompatActivity {
    ImageButton imageButton;
    MaterialButton maps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        maps=findViewById(R.id.button);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(detail_activity.this,maps_activity.class);
                startActivity(intent);
            }
        });


     imageButton=findViewById(R.id.icons);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}