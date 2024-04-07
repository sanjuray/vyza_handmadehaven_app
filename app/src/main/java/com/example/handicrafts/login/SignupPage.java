package com.example.handicrafts.login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.handicrafts.Home;
import com.example.handicrafts.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignupPage extends AppCompatActivity {
   ImageView backToHome,gotoLogin,userImage;
      Button signUp;
    GenderSpinnerAdapter genderSpinnerAdapter;
    TextInputEditText username,useremail,userpassword,userpincode,usercity;

    StateSpinnerAdapter stateSpinnerAdapter;
    TextView addImage;
      List<GenderSpinnerDataClass> gender = new ArrayList<>();

      List<GenderSpinnerDataClass> state = new ArrayList<>();

    Spinner genderDropDown,stateDropDown;
    String select_state;
    String select_gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        init();


        genderSpinner();

        stateSpinner();
        genderDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select_gender= String.valueOf(genderDropDown.getItemAtPosition(i));



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        stateDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 select_state = String.valueOf(stateDropDown.getSelectedItem());


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddata( );
            }

        });
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

    }



     public void adddata() {
        //left side name same as of the table headings
        String name=username.getText().toString().trim();
        String password=userpassword.getText().toString().trim();
        String email=useremail.getText().toString().trim();
        String gender=  select_gender;


       String state= select_state;

        String pincode=userpincode.getText().toString().trim();
        String city=usercity.getText().toString().trim();
        if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(password)&&TextUtils.isEmpty(email)&&TextUtils.isEmpty(gender)&&TextUtils.isEmpty(city)){
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            userpincode.setError("all fields are required");
            useremail.requestFocus();
            userpassword.requestFocus();
        }
        else {
            Addtotable(name,email,password,city,pincode,gender,state);
        }




    }

    private void Addtotable(String name, String email, String password, String city, String pincode , String gender, String state) {
        String url = "https://handmadehavens.com/user.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // JSONStringer stringer=new JSONStringer();
                // JSONObject object = new JSONObject(response);
                // String message = object.getString("message");


                Toast.makeText(SignupPage.this,response.toString(), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignupPage.this, "Not sucessfull", Toast.LENGTH_SHORT).show();

            }
        }) {

                @Override
                public String getBodyContentType() {

                    return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("city", city);
                params.put("pincode", pincode);

                params.put("state",state);
                params.put("gender",gender);



                return params;
            }
        };
        requestQueue.add(request);
    }


        public void genderSpinner () {
            gender.add(new GenderSpinnerDataClass("Gender", R.color.white));
            gender.add(new GenderSpinnerDataClass("Male", R.drawable.ic_male_24));
            gender.add(new GenderSpinnerDataClass("Female", R.drawable.ic_female_24));
            gender.add(new GenderSpinnerDataClass("other", R.color.white));


            genderSpinnerAdapter = new GenderSpinnerAdapter(SignupPage.this, gender);
            genderDropDown.setAdapter(genderSpinnerAdapter);

        }



    public void stateSpinner () {

            state.add(new GenderSpinnerDataClass("Select State", R.color.white));
            state.add(new GenderSpinnerDataClass("Himachal Pradesh", R.color.white));
            state.add(new GenderSpinnerDataClass("Maharashtra", R.color.white));
        state.add(new GenderSpinnerDataClass("Tamil Nadu", R.color.white));
        state.add(new GenderSpinnerDataClass("Madhya Pradesh", R.color.white));
        state.add(new GenderSpinnerDataClass("Sikkim", R.color.white));
        state.add(new GenderSpinnerDataClass("Nagaland", R.color.white));
        state.add(new GenderSpinnerDataClass("Utrakhand", R.color.white));
        state.add(new GenderSpinnerDataClass("Telangana", R.color.white));
        state.add(new GenderSpinnerDataClass("Assam", R.color.white));
            state.add(new GenderSpinnerDataClass("Chhattisgarh ", R.color.white));
            state.add(new GenderSpinnerDataClass("Punjab ", R.color.white));
            state.add(new GenderSpinnerDataClass("Chandigarh ", R.color.white));
            state.add(new GenderSpinnerDataClass("Rajasthan  ", R.color.white));
            state.add(new GenderSpinnerDataClass("Goa", R.color.white));
            state.add(new GenderSpinnerDataClass("Gujarat", R.color.white));
            state.add(new GenderSpinnerDataClass("Arunachal Pradesh", R.color.white));
            state.add(new GenderSpinnerDataClass("Mizoram", R.color.white));
            state.add(new GenderSpinnerDataClass("Karnataka   ", R.color.white));
            state.add(new GenderSpinnerDataClass("Andhra Pradesh  ", R.color.white));
            state.add(new GenderSpinnerDataClass("Odisha", R.color.white));
            state.add(new GenderSpinnerDataClass("Haryana", R.color.white));
            state.add(new GenderSpinnerDataClass("Jharkhand", R.color.white));
            state.add(new GenderSpinnerDataClass("Kerala", R.color.white));
            state.add(new GenderSpinnerDataClass("Mizoram", R.color.white));
            state.add(new GenderSpinnerDataClass("West Bengal", R.color.white));
            state.add(new GenderSpinnerDataClass("Meghalaya", R.color.white));
            state.add(new GenderSpinnerDataClass("Manipur", R.color.white));
            state.add(new GenderSpinnerDataClass("Jammu and Kashmir", R.color.white));
            state.add(new GenderSpinnerDataClass("Andaman and Nicobar", R.color.white));

        stateSpinnerAdapter = new StateSpinnerAdapter(state, SignupPage.this);
            stateDropDown.setAdapter(stateSpinnerAdapter);



        }


        public void init () {
            backToHome = findViewById(R.id.img_back);
            gotoLogin = findViewById(R.id.img_gotoLogin);
            signUp = findViewById(R.id.btn_signup);
            userImage = findViewById(R.id.img_user);

            addImage = findViewById(R.id.txt_addImage);
            genderDropDown = findViewById(R.id.edt_dropdown_gender);
            username = findViewById(R.id.edt_username);
            userpassword = findViewById(R.id.edt_password);
            usercity = findViewById(R.id.edt_city);
            userpincode = findViewById(R.id.edt_pincode);
            useremail = findViewById(R.id.edt_email);
            stateDropDown = findViewById(R.id.edt_dropdown_state);

        }


}