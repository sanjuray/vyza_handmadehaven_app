package com.example.handicrafts.detail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.utils.Utils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.handicrafts.R;

//import com.phonepe.intent.sdk.api.B2BPGRequest;
//import com.phonepe.intent.sdk.api.B2BPGRequestBuilder;
//import com.phonepe.intent.sdk.api.PhonePe;
//import com.phonepe.intent.sdk.api.PhonePeInitException;
//import com.phonepe.intent.sdk.api.models.PhonePeEnvironment;
import com.phonepe.intent.sdk.api.B2BPGRequest;
import com.phonepe.intent.sdk.api.B2BPGRequestBuilder;
import com.phonepe.intent.sdk.api.PhonePe;
import com.phonepe.intent.sdk.api.PhonePeInitException;
import com.phonepe.intent.sdk.api.models.PhonePeEnvironment;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;


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
import java.util.UUID;

import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

public class detail_view extends AppCompatActivity implements PaymentResultWithDataListener /*PaymentResultListener*/ {
    ImageView images;
    ImageView backButton;
    TextView description, state, city, title, price, discount,price2;
    LinearLayout buyNow;
    LottieAnimationView animationView,animationViews;

    Checkout checkout;

    String merchantId,merchantTransactionId,saltIndex,apiEndPoint;
    B2BPGRequest b2BPGRequest;


    float paymentAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_new);

        price2 = findViewById(R.id.title2);
        backButton = findViewById(R.id.backed);
        images = findViewById(R.id.detail_images);
        description = findViewById(R.id.description);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        title = findViewById(R.id.detail_title);
        price = findViewById(R.id.price);
        discount = findViewById(R.id.detail_discount);
        animationView = findViewById(R.id.lottie);

        buyNow = findViewById(R.id.detail_buy_now);
        animationView.setAnimation(R.raw.lottie4);
        animationView.playAnimation();


        animationViews=findViewById(R.id.review_anim);
        animationViews.setAnimation(R.raw.review);
        animationViews.playAnimation();


        String product_id = getIntent().getStringExtra("product_id");
        String url = "https://handmadehavens.com/detail.php?product_id=" + product_id;

        fetchProductDetail(url);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    initPhonePe();
                } catch (PhonePeInitException e) {
                    throw new RuntimeException(e);
                }
//               initRazorpay();
            }

        });
    }

    private void initRazorPay(){
        Checkout.preload(getApplicationContext());
        checkout =new Checkout();
        checkout.setKeyID("rzp_test_Nqy8gmPWtyPySL");
        checkout.setImage(R.drawable.logo);
        paymentAmount = Float.parseFloat(price.getText().toString());
        final Activity activity = this;
        // Initialize client
//        RazorpayClient instance = new RazorpayClient("key_id", "key_secret");

        JSONObject orderRequest = new JSONObject();
        try {
            orderRequest.put("name","Vyza Solutions");
            orderRequest.put("description","Reference no. 007");
//            orderRequest.put("theme.color","#35EDF9");
            orderRequest.put("currency","INR");
            orderRequest.put("amount",paymentAmount*100.00);
            orderRequest.put("contact","6874518955");
            orderRequest.put("email","customer@gmail.com");

//            orderRequest.put("order_id","order_id");

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            orderRequest.put("retry", retryObj);

            JSONObject prefill = new JSONObject();
            prefill.put("email","customer@gmail.com");
            prefill.put("contact","6874518955");

            orderRequest.put("prefill",prefill);
            checkout.open(activity,orderRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Checker","Error in gateway");
        }
    }
// Used when paymentDataListener is used for razorpay
//    @Override
//    public void onPaymentSuccess(String razorpayPaymentId) {
//        Toast.makeText(getApplicationContext(),"The payment id:"+razorpayPaymentId+" is Successful",Toast.LENGTH_SHORT).show();
//        Checkout.clearUserData(this);
//    }
//
//    @Override
//    public void onPaymentError(int code, String response) {
//        String msg;
//        if(code == Checkout.NETWORK_ERROR) msg = "Network issue";
//        else if(code == Checkout.INVALID_OPTIONS) msg = "issue in JSON obj";
//        else if(code == Checkout.PAYMENT_CANCELED) msg = "payment cancelled";
//        else msg = " Some TLS";
//        Toast.makeText(getApplicationContext(),msg+" "+"Failed"+response,Toast.LENGTH_SHORT).show();
//        Checkout.clearUserData(this);
//    }

// for razorpay
    @Override
    public void onPaymentSuccess(String razorpayPaymentId, PaymentData paymentData) {
        //update the orders table with paymentData and verify the payment signature from PaymentData
        Toast.makeText(getApplicationContext(),"Success, the payment id:"+razorpayPaymentId+" is Successful",Toast.LENGTH_SHORT).show();
        Checkout.clearUserData(this);
    }

    @Override
    public void onPaymentError(int code, String response, PaymentData paymentData) {
        String msg;
        if(code == Checkout.NETWORK_ERROR) msg = "Network issue";
        else if(code == Checkout.INVALID_OPTIONS) msg = "issue in JSON obj";
        else if(code == Checkout.PAYMENT_CANCELED) msg = "payment cancelled";
        else msg = " Some TLS";
        Toast.makeText(getApplicationContext(),msg+" "+"Failed"+response,Toast.LENGTH_SHORT).show();
        Checkout.clearUserData(this);
    }

    private void initPhonePe() throws PhonePeInitException {

        merchantId = "PGTESTPAYUAT";
        merchantTransactionId = "T"+String.valueOf(System.currentTimeMillis());
        saltIndex = "099eb0cd-02cf-4e2a-8aca-3e6c6aff0399";
        apiEndPoint = "/pg/v1/pay";

        PhonePe.init(this, PhonePeEnvironment.SANDBOX, merchantId, null);
        Log.v("Checker","onCreate:"+PhonePe.getPackageSignature());
//
        paymentAmount = Float.parseFloat(price.getText().toString());
        JSONObject data = new JSONObject();
        try {

            data.put("merchantTransactionId", merchantTransactionId);
            data.put("merchantId", merchantId);
//            data.put("merchantUserId", "9876543"); --> optional for testing used for getting card details and etc
            data.put("amount", paymentAmount*100);
            data.put("mobileNumber", "9876543210");
            data.put("callbackUrl", "https://webhook.site/bf0f91b3-280b-4e90-a56f-a2dd81f7e8ee");

            JSONObject paymentInstrument = new JSONObject();
            paymentInstrument.put("type", "PAY_PAGE");
//            paymentInstrument.put("targetApp","com.phonepe.simulator");

            data.put("paymentInstrument", paymentInstrument);

            JSONObject deviceContext = new JSONObject();
            deviceContext.put("deviceOS","ANDROID");

            data.put("deviceContext",deviceContext);

            String payLoadBase64 = Base64.encodeToString(
                    data.toString().getBytes(Charset.defaultCharset()),
                    Base64.NO_WRAP);

            Log.v("Checker","initPhonePe: payload"+payLoadBase64+" merchant transaction Id: "+merchantTransactionId);

            String checkSum = sha256(payLoadBase64 + apiEndPoint + saltIndex) + "###1";// ??? + saltIndex;
            Log.v("Checker","CheckSum: "+checkSum);

            b2BPGRequest = new B2BPGRequestBuilder()
                    .setData(payLoadBase64)
                    .setChecksum(checkSum)
                    .setUrl(apiEndPoint)
                    .build();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        try {
//            Intent intent = PhonePe.getImplicitIntent(getApplicationContext(), b2BPGRequest, "com.phonepe.simulator");
//            startActivityForResult(intent, 1);
            startActivityForResult(PhonePe.getImplicitIntent(getApplicationContext(),b2BPGRequest,""),1);
            Log.v("Checker","initPhonePe Intent");
        } catch (PhonePeInitException e) {
            Log.v("CheckerIntentCall","initPhonePe123: ");
            e.printStackTrace();
        }

    }


// PhonePe
    private String sha256(String input) {
        byte[] bytes = input.getBytes(Charset.forName("UTF-8"));
        MessageDigest md;
        StringBuilder sb = new StringBuilder();
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(bytes);
            for (byte b : digest)  sb.append(String.format("%02x", b));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

// After transaction through PhonePe follow-up
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("Checker","hello there from ActivityResult");
        if (requestCode == 1) {
            Log.v("Checker","hello there from ActivityResult_req_1");
            if (resultCode == RESULT_OK) {

                Log.v("Checker","hello there from ActivityResult_OK");

                Toast.makeText(this, "Payment Completed!", Toast.LENGTH_SHORT).show();
//                checkStatus();
//                String response = data.getStringExtra("response");
//                Log.i("Phone Pe", "response: " + response);
//
//                try {
//                    JSONObject jsonResponse = new JSONObject(response);
//                    String status = jsonResponse.getString("status");
//                    String message = jsonResponse.getString("message");
//                    String transactionId = jsonResponse.getString("transactionId");
//
//                    Log.i("PhonePe", "status: " + status);
//                    Log.i("PhonePe", "message: " + message);
//                    Log.i("PhonePe", "transactionId: " + transactionId);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.i("Phone Pe", "Payment cancelled");
            }
        }
    }

    private void checkStatus() {
        String checkSum = sha256("/pg/v1/status/" + merchantId + "/" + merchantTransactionId + saltIndex) + "###1";
        Log.v("Checker","CheckStatus status:"+checkSum);
    }

    private void fetchProductDetail(String url) {
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


}




