package com.example.handicrafts.login;

import static com.android.volley.Request.Method.POST;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.handicrafts.Home;
import com.example.handicrafts.R;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends AppCompatActivity {
    ImageView backToHome,gotoSignUp;
    Button login;
    TextInputEditText confirm_email,confirm_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login_user();


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
        JSONObject object=new JSONObject();
        try {
            object.put("email",email);
            object.put("password",password);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest request=new JsonObjectRequest(POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean success = response.getBoolean("success");
                    String message = response.getString("message");
                    if (success) {
                        int userid = response.getInt("user_id");
                        send(userid);
                    } else {
                        Toast.makeText(LoginPage.this, message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginPage.this, "User not registered!!please register first", Toast.LENGTH_SHORT).show();

            }
        });
        Volley.newRequestQueue(this).add(request);


    }

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
        confirm_email=findViewById(R.id.edt_email);
        confirm_password=findViewById(R.id.edt_password);



    }
}