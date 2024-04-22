package com.example.handicrafts.login;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.handicrafts.Home;
import com.example.handicrafts.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {
    ImageView backToHome,gotoSignUp;
    Button login;
    TextInputEditText confirm_email,confirm_password;


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
                login_user();

            }
        });
    }

    private void login_user() {
        String email=confirm_email.getText().toString();
        String password=confirm_password.getText().toString();
        if(TextUtils.isEmpty(email)&&TextUtils.isEmpty(password)){
            confirm_password.setError("please enter registered mail");
            confirm_email.setError("please enter registered  password");
            confirm_email.requestFocus();
            confirm_password.requestFocus();
        }
        else {
            loguser(email,password);
        }


    }

    private void loguser(String email, String password) {
        String url="https://handmadehavens.com/loginapp.php";

        RequestQueue requestQueue = Volley.newRequestQueue(this);


            StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            int userId = jsonResponse.getInt("user_id");
                            send(userId);
                            Intent intent = new Intent(LoginPage.this, Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            if (!success) {
                                Toast.makeText(LoginPage.this, "User not registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginPage.this, SignupPage.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }


            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(LoginPage.this, "Check your network", Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> data=new HashMap<>();
                    data.put("email",email);
                    data.put("password",password);
                    return data;
                }
            };

            requestQueue.add(request);
    };


        public void send(int userid) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userid);
        editor.apply();
    }

    private void init(){
        backToHome = findViewById(R.id.img_backtoHome);
        gotoSignUp = findViewById(R.id.img_gotoSignUp);
        login = findViewById(R.id.btn_login);
        confirm_email=findViewById(R.id.mail);
        confirm_password=findViewById(R.id.pass);



    }
}