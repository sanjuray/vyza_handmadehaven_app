package com.example.handicrafts.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.handicrafts.Home;
import com.example.handicrafts.R;

import java.util.ArrayList;
import java.util.List;

public class SignupPage extends AppCompatActivity {
    private ImageView backToHome,gotoLogin,userImage;
    private Button signUp;
    GenderSpinnerAdapter genderSpinnerAdapter;
    LanguageSpinnerAdapter languageSpinnerAdapter;
    StateSpinnerAdapter stateSpinnerAdapter;
    private TextView addImage;
    private List<GenderSpinnerDataClass> gender = new ArrayList<>();
    private List<GenderSpinnerDataClass> language = new ArrayList<>();
    private List<GenderSpinnerDataClass> state = new ArrayList<>();

    private Spinner genderDropDown,stateDropDown,languageDropDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        init();


        genderSpinner();
        languageSpinner();
        stateSpinner();
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupPage.this, Home.class));
            }
        });
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupPage.this, LoginPage.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupPage.this,"SignUp Button Pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void genderSpinner() {
        gender.add(new GenderSpinnerDataClass("Gender",R.color.white));
        gender.add(new GenderSpinnerDataClass("Male",R.drawable.ic_male_24));
        gender.add(new GenderSpinnerDataClass("Female",R.drawable.ic_female_24));
        gender.add(new GenderSpinnerDataClass("other",R.color.white));

        genderSpinnerAdapter = new GenderSpinnerAdapter(SignupPage.this,gender);
        genderDropDown.setAdapter(genderSpinnerAdapter);
    }

    private void stateSpinner(){
        state.add(new GenderSpinnerDataClass("Select State",R.color.white));
        state.add(new GenderSpinnerDataClass("Himachal Pradesh",R.color.white));
        state.add(new GenderSpinnerDataClass("Maharashtra",R.color.white));
        state.add(new GenderSpinnerDataClass("Chhattisgarh ",R.color.white));
        state.add(new GenderSpinnerDataClass("Punjab ",R.color.white));
        state.add(new GenderSpinnerDataClass("Chandigarh ",R.color.white));
        state.add(new GenderSpinnerDataClass("Rajasthan  ",R.color.white));
        state.add(new GenderSpinnerDataClass("Goa",R.color.white));
        state.add(new GenderSpinnerDataClass("Gujarat",R.color.white));
        state.add(new GenderSpinnerDataClass("Arunachal Pradesh",R.color.white));
        state.add(new GenderSpinnerDataClass("Mizoram",R.color.white));
        state.add(new GenderSpinnerDataClass("Karnataka   ",R.color.white));
        state.add(new GenderSpinnerDataClass("Andhra Pradesh  ",R.color.white));
        state.add(new GenderSpinnerDataClass("Odisha",R.color.white));
        state.add(new GenderSpinnerDataClass("Haryana",R.color.white));
        state.add(new GenderSpinnerDataClass("Jharkhand",R.color.white));
        state.add(new GenderSpinnerDataClass("Kerala",R.color.white));
        state.add(new GenderSpinnerDataClass("Mizoram",R.color.white));
        state.add(new GenderSpinnerDataClass("West Bengal",R.color.white));
        state.add(new GenderSpinnerDataClass("Meghalaya",R.color.white));
        state.add(new GenderSpinnerDataClass("Manipur",R.color.white));
        state.add(new GenderSpinnerDataClass("Jammu and Kashmir",R.color.white));
        state.add(new GenderSpinnerDataClass("Andaman and Nicobar",R.color.white));

        stateSpinnerAdapter = new StateSpinnerAdapter(state,SignupPage.this);
        stateDropDown.setAdapter(stateSpinnerAdapter);


    }
    private void languageSpinner() {
        language.add(new GenderSpinnerDataClass("Select Mother Tongue",R.color.white));
        language.add(new GenderSpinnerDataClass("English",R.color.white));
        language.add(new GenderSpinnerDataClass("Hindi",R.color.white));
        language.add(new GenderSpinnerDataClass("Marathi",R.color.white));
        language.add(new GenderSpinnerDataClass("Kannada",R.color.white));
        language.add(new GenderSpinnerDataClass("Malayalam",R.color.white));
        language.add(new GenderSpinnerDataClass("Punjabi",R.color.white));
        language.add(new GenderSpinnerDataClass("Telugu",R.color.white));
        language.add(new GenderSpinnerDataClass("Tamil",R.color.white));
        language.add(new GenderSpinnerDataClass("Bengali",R.color.white));

        languageSpinnerAdapter = new LanguageSpinnerAdapter(SignupPage.this, language);
        languageDropDown.setAdapter(languageSpinnerAdapter);
    }

    private void init(){
        backToHome = findViewById(R.id.img_back);
        gotoLogin = findViewById(R.id.img_gotoLogin);
        signUp = findViewById(R.id.btn_signup);
        userImage = findViewById(R.id.img_user);
        addImage = findViewById(R.id.txt_addImage);
        genderDropDown = findViewById(R.id.edt_dropdown_gender);
        languageDropDown = findViewById(R.id.edt_dropdown_language);
        stateDropDown = findViewById(R.id.edt_dropdown_state);

    }
}