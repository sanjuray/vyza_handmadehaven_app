package com.example.handicrafts.profile;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularIntArray;

import com.example.handicrafts.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class edit_profile extends AppCompatActivity {
    ShimmerFrameLayout shimmerFrameLayout;
    ImageButton imageButton;

    CircleImageView imageView;


    TextInputEditText name,mail,phone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);
        imageButton=findViewById(R.id.back);
        name=findViewById(R.id.add_name);
        mail=findViewById(R.id.add_mail);
        imageView=findViewById(R.id.user_images);
        phone=findViewById(R.id.add_phone);

        SharedPreferences preferences1=getSharedPreferences("signup",MODE_PRIVATE);
        String image=preferences1.getString("images","");
     /* if(image!=null){
            Uri images= Uri.parse(image);
            try {
                InputStream inputStream=getContentResolver().openInputStream(images);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            if (image==null){

            }




          imageView.setImageResource(R.drawable.baseline_person_24);

        }*/


        SharedPreferences preferences=getSharedPreferences("edit",MODE_PRIVATE);

        String store_name=preferences.getString("name","");
        String store_mail=preferences.getString("mail","");
        String store_phone=preferences.getString("phone","");
        name.setText(store_name);
        mail.setText(store_mail);
        phone.setText(store_phone);





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