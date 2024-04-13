package com.example.handicrafts.profile;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.handicrafts.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class detail_activity extends AppCompatActivity {
    ImageButton imageButton;

    MaterialButton maps,save;
    TextInputEditText name,adress_line,state,city,pincode,contact;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        maps=findViewById(R.id.button);
        name=findViewById(R.id.detail_name);
        adress_line=findViewById(R.id.detail_address);
        state=findViewById(R.id.detail_state);
        city=findViewById(R.id.detail_city);
        pincode=findViewById(R.id.detail_pin);
        contact=findViewById(R.id.deatil_contact);
        save=findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String save_name=name.getText().toString().trim();
                String save_addess=adress_line.getText().toString().trim();
                String save_city=city.getText().toString().trim();
                String save_state=state.getText().toString().trim();
                String save_pin=pincode.getText().toString().trim();
                String save_contact=contact.getText().toString().trim();
                if(TextUtils.isEmpty(save_name) && TextUtils.isEmpty(save_addess)){
                    name.setError("please enter name");
                    adress_line.setError("please enter address");
                    name.requestFocus();
                    adress_line.requestFocus();

                } else if (TextUtils.isEmpty(save_city)&&TextUtils.isEmpty(save_state)) {
                     city.setError("please enter city");
                    state.setError("please enter state");
                    city.requestFocus();
                    state.requestFocus();

                } else if (TextUtils.isEmpty(save_pin)&&TextUtils.isEmpty(save_contact)&&save_contact.length()==10) {
                    pincode.setError("please enter pincode");
                    contact.setError("please enter contact");
                    pincode.requestFocus();
                    contact.requestFocus();

                }
                else {
                    save_shared(save_name,save_addess,save_city,save_state,save_pin,save_contact);
                }

            }
        });









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

    private void save_shared(String save_name, String save_addess, String save_city, String save_state, String save_pin, String save_contact) {
       // to store loacally the data
        SharedPreferences preferences=getSharedPreferences("Edit_prefernece", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("detail_name",save_name);
        editor.putString("detail_address",save_addess);
        editor.putString("detail_city",save_city);
        editor.putString("detail_state",save_state);
        editor.putString("detail_pin",save_pin);
        editor.putString("detail_contact",save_contact);
        editor.apply();
    }
}