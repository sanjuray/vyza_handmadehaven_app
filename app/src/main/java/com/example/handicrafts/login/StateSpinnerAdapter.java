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

public class StateSpinnerAdapter extends BaseAdapter {
    private List<GenderSpinnerDataClass> state;
    private Context context;
    public StateSpinnerAdapter(List<GenderSpinnerDataClass> state,Context context){
        this.state = state;
        this.context = context;
    }
    @Override
    public int getCount() {
        return state.size();
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
        genderImage.setImageResource(state.get(i).gender_image);
        genderText.setText(state.get(i).genderText);
        return rootView;
    }

}
