package com.example.handicrafts.fav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.handicrafts.R;

public class fav_activity extends Fragment {
    Animation animation;
    LottieAnimationView animationView;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_fav,null);
        animationView=view.findViewById(R.id.animation1);
        textView=view.findViewById(R.id.fav);
        textView.setText("No items to display");
        animation= AnimationUtils.loadAnimation(getContext(),R.anim.anim1);
        textView.startAnimation(animation);

        animationView.setAnimation(R.raw.lottie_2);
        animationView.playAnimation();

        return view;
    }





}
