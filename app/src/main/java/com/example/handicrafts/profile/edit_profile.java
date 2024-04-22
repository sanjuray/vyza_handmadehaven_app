package com.example.handicrafts.profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.handicrafts.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.textfield.TextInputEditText;

public class edit_profile extends AppCompatActivity {
    ShimmerFrameLayout shimmerFrameLayout;
    ImageButton imageButton;

    TextInputEditText name,mail,phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);
        imageButton=findViewById(R.id.back);
        SharedPreferences preferences=getSharedPreferences("edit",MODE_PRIVATE);

        String store_name=preferences.getString("name","");
        String store_mail=preferences.getString("mail","");
        String store_phone=preferences.getString("phone","");
        name.setText(store_name);
        mail.setText(store_mail);
        phone.setText(store_phone);


        name=findViewById(R.id.add_name);
        mail=findViewById(R.id.add_mail);
        phone=findViewById(R.id.add_phone);


        String pref_name=name.getText().toString();
        String pre_mail=mail.getText().toString();
        String pref_phone=mail.getText().toString();
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("name",pref_name);
        editor.putString("mail",pre_mail);
        editor.putString("phone",pref_phone);
        editor.apply();




        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}