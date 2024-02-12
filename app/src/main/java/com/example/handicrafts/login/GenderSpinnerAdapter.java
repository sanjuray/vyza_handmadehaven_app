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

public class GenderSpinnerAdapter extends BaseAdapter {
    private Context adaptercontext;
    private List<GenderSpinnerDataClass> gender;
    public GenderSpinnerAdapter(Context context,List<GenderSpinnerDataClass> gender){
        this.adaptercontext = context;
        this.gender = gender;
    }
    @Override
    public int getCount() {
        return gender != null ? gender.size():0;
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
        View rootView = LayoutInflater.from(adaptercontext).inflate(R.layout.layout_general_spinner, viewGroup,false);
        TextView genderText = rootView.findViewById(R.id.txt_gender);
        ImageView genderImage = rootView.findViewById(R.id.img_gender);
        genderImage.setImageResource(gender.get(i).gender_image);
        genderText.setText(gender.get(i).genderText);
        return rootView;
    }
}
