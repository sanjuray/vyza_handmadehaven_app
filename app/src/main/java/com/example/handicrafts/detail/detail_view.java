package com.example.handicrafts.detail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.handicrafts.R;
import com.example.handicrafts.login.SignupPage;
import com.example.handicrafts.view.view_data;
import com.phonepe.intent.sdk.api.B2BPGRequest;
import com.phonepe.intent.sdk.api.B2BPGRequestBuilder;
import com.phonepe.intent.sdk.api.PhonePe;
import com.phonepe.intent.sdk.api.PhonePeInitException;
import com.phonepe.intent.sdk.api.models.PhonePeEnvironment;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

public class detail_view extends AppCompatActivity implements PaymentResultListener {
    ImageView images;
    ImageView backbutton,test;
    TextView description, state, city, title, price, discount,price2;
    LinearLayout buyNow;
    LottieAnimationView animationView,review_anims,loading;

    float realAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_new);
        review_anims=findViewById(R.id.review_anim);
        test=findViewById(R.id.share);
        review_anims.setAnimation(R.raw.review);
        review_anims.playAnimation();
        price2=findViewById(R.id.title2);
        loading=findViewById(R.id.load);
        loading.setVisibility(View.VISIBLE);
        loading.setAnimation(R.raw.loading);
        loading.playAnimation();


        backbutton = findViewById(R.id.backed);
        images = findViewById(R.id.detail_images);
        description = findViewById(R.id.description);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        title = findViewById(R.id.detail_title);
        price = findViewById(R.id.price);
        discount = findViewById(R.id.detail_discount);
        animationView = findViewById(R.id.lottie);
        animationView.setAnimation(R.raw.lottie4);
        buyNow = findViewById(R.id.detail_buy_now);
        animationView.playAnimation();

        String merchantId = "PGTESTPAYUAT";
        String merchantTransactionId = "98321";
        String apiEndPoint = "/pg/v1/pay";
        String salt = "099eb0cd-02cf-4e2a-8aca-3e6c6aff0399";
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                razorPay();
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(detail_view.this, SignupPage.class);
                startActivity(i);
            }
        });


        String product_id = getIntent().getStringExtra("product_id");
        String url = "https://handmadehavens.com/detail.php?product_id=" + product_id;

        fetchProductDetail(url);



//        PhonePe.init(this, PhonePeEnvironment.SANDBOX, merchantId, null);
//
//        realAmount = Float.parseFloat(price.getText().toString());
//        JSONObject data = new JSONObject();
//        try {
//            data.put("merchantTransactionId", merchantTransactionId);
//
//            data.put("merchantId", merchantId);
//            data.put("merchantUserId", "9876543");
//
//            data.put("amount", realAmount*100.00);
//            data.put("mobileNumber", "8104326554");
//            data.put("callbackUrl", "https://webhook.site/cc23d6b1-bfb7-4902-90fd-18a1db93f891");
//
//            JSONObject paymentInstrument = new JSONObject();
//            paymentInstrument.put("type", "PAY_PAGE");
//            data.put("paymentInstrument", paymentInstrument);
//
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//        String payloadBase64 = android.util.Base64.encodeToString(
//                data.toString().getBytes(Charset.defaultCharset()), android.util.Base64.NO_WRAP
//        );
//
//        String checksum = sha256(payloadBase64 + apiEndPoint + salt) + "###1";
//
//        B2BPGRequest b2BPGRequest = new B2BPGRequestBuilder()
//                .setData(payloadBase64)
//                .setChecksum(checksum)
//                .setUrl(apiEndPoint)
//                .build();
//
//        buyNow.setOnClickListener(view -> {
//            try {
//                data.put("amount", realAmount*100.00);
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//            try {
//                Intent intent = PhonePe.getImplicitIntent(getApplicationContext(), b2BPGRequest, "");
//                startActivityForResult(intent, 1);
//            } catch (PhonePeInitException e) {
//                e.printStackTrace();
//            }
//        });



    } private void fetchProductDetail(String url) {
        // Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a JSON object response from the provided URL
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Parse the JSON response and populate UI elements with product details
                        try {
                            String productDescription = response.getString("description");
                            String productState = response.getString("state");
                            String productCity = response.getString("city");
                            String productName = response.getString("name");
                            String productPrice = response.getString("price");
                            String productDiscount = response.getString("discount");
                            String productImage = response.getString("images");

                            // Populate UI elements
                            description.setText(productDescription);
                            state.setText(productState);
                            city.setText(productCity);
                            title.setText(productName);
                            price.setText(productPrice);
                            discount.setText(productDiscount);
                            price2.setText(productName);


                            // Load product image using Glide
                            Glide.with(detail_view.this)
                                    .load(productImage)
                                    .error(R.drawable.account) // Placeholder image in case of error
                                    .into(images);
                            loading.setVisibility(View.GONE);
                            loading.cancelAnimation();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Handle JSON parsing error
                            Toast.makeText(detail_view.this, "Error parsing product details", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(detail_view.this, "Error fetching product details", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        queue.add(jsonObjectRequest);
    }


    private void razorPay(){
        realAmount = Float.parseFloat(price.getText().toString());
        Checkout checkout =new Checkout();
        checkout.setKeyID("-----Enter ApI key-----");

        JSONObject object = new JSONObject();
        try {
            object.put("Name","Ujjwal Tiwari");
            object.put("Description","Gateway");
            object.put("theme color","#35EDF9");
            object.put("currency","INR");
            object.put("amount",realAmount*100.00);
            object.put("Contact","6874518955");
            object.put("email","ujjwal@gmail.com");
            checkout.open(detail_view.this,object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String sha256(String input) {
        byte[] bytes = input.getBytes(Charset.forName("UTF-8"));
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(bytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String response = data.getStringExtra("response");
                Log.i("PHONPE", "response: " + response);

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String status = jsonResponse.getString("status");
                    String message = jsonResponse.getString("message");
                    String transactionId = jsonResponse.getString("transactionId");

                    Log.i("PHONPE", "status: " + status);
                    Log.i("PHONPE", "message: " + message);
                    Log.i("PHONPE", "transactionId: " + transactionId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.i("PHONPE", "Payment cancelled");
            }
        }
    }
}




