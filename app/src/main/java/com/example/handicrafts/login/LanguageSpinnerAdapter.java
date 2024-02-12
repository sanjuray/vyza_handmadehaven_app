package com.example.handicrafts.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handicrafts.R;

import java.util.List;

import kotlin.jvm.internal.PropertyReference0Impl;

public class LanguageSpinnerAdapter extends BaseAdapter {
    private List<GenderSpinnerDataClass> lang;
    private Context context;
    public LanguageSpinnerAdapter(Context context,List<GenderSpinnerDataClass> lang){
        this.context = context;
        this.lang = lang;
    }
    @Override
    public int getCount() {
        return lang.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_general_spinner, viewGroup,false);
        TextView genderText = rootView.findViewById(R.id.txt_gender);
        ImageView genderImage = rootView.findViewById(R.id.img_gender);
        genderImage.setImageResource(lang.get(i).gender_image);
        genderText.setText(lang.get(i).genderText);
        return rootView;
    }
}
