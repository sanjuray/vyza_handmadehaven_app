package com.example.handicrafts.login;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.handicrafts.Home;
import com.example.handicrafts.R;
import com.example.handicrafts.splash;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignupPage extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST =1 ;
    ImageView backToHome,gotoLogin,userImage;
      Button signUp;
    GenderSpinnerAdapter genderSpinnerAdapter;
    TextInputEditText username,useremail,userpassword,userpincode,usercity;

    StateSpinnerAdapter stateSpinnerAdapter;
    TextView addImage;
    SharedPreferences preferences;
      List<GenderSpinnerDataClass> gender = new ArrayList<>();

      List<GenderSpinnerDataClass> state = new ArrayList<>();

    Spinner genderDropDown,stateDropDown;
    String select_state;
    String select_gender;
    Boolean islogin;
    static final int PERMISSION_REQUEST_CODE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        init();


        genderSpinner();

        stateSpinner();


        //String image_uri=preferences.getString("image","");
       /* if(image_uri!=null){
            Uri image=Uri.parse(image_uri);
            try {

                InputStream inputStream = getContentResolver().openInputStream(image);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                userImage.setImageBitmap(bitmap);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("images",image_uri);
            } catch (FileNotFoundException e) {
                userImage.setImageResource(R.drawable.baseline_person_24);
                throw new RuntimeException(e);

            }

        }else {
            userImage.setImageResource(R.drawable.baseline_person_24);
        }*/

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

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(SignupPage.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(SignupPage.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERMISSION_REQUEST_CODE);
                }

                else {
                    chosefromgallery();
                }



            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddata( );

            }

        });


        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupPage.this,LoginPage.class);
                startActivity(intent);


            }
        });

    }

    private void chosefromgallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                 chosefromgallery();
            } else {

                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            Uri imageuri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(imageuri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                userImage.setImageBitmap(bitmap);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("image",imageuri.toString());
                editor.apply();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
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


        if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(password)&&TextUtils.isEmpty(email)&&TextUtils.isEmpty(city)){
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            userpincode.setError("all fields are required");
            useremail.requestFocus();
            usercity.requestFocus();
            userpassword.requestFocus();


        }

        else {
            Addtotable(name,email,password,city,pincode,gender,state);
        }




    }

    private void Addtotable(String name, String email, String password, String city, String pincode , String gender, String state) {

       String url = "https://handmadehavens.com/newsignup.php";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if(success) {
                        int userId = jsonResponse.getInt("user_id");
                        Toast.makeText(SignupPage.this, "signed up with"+userId, Toast.LENGTH_SHORT).show();

                        islogin = true;
                        senddata(userId);
                        shared();
                        Intent intent = new Intent(SignupPage.this, Home.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignupPage.this, "Not Succesfull", Toast.LENGTH_SHORT).show();

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

    public void shared() {
        SharedPreferences preferences=getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("name", username.getText().toString());
        editor.apply();
    }

    public void senddata(int userid) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userid);
        editor.apply();



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
        state.add(new GenderSpinnerDataClass("Uttrakhand", R.color.white));
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