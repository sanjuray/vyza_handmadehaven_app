package com.example.handicrafts.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.handicrafts.R;

import java.util.ArrayList;

public class grid_adapter extends RecyclerView.Adapter<grid_adapter.view> {

    ArrayList<data> models;
    Context context;

    public grid_adapter(ArrayList<data> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_newitems,parent,false);
        return new view(view);

    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {
        data m=models.get(position);
        holder.txt.setText(m.getName());
        holder.price.setText(m.getPrice());
        holder.discount.setText(m.getDiscount());
        Glide.with(context).load(m.getImages()).into(holder.img);



    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class view extends RecyclerView.ViewHolder{
        ImageView  img;
        TextView txt, price,discount;


        public view(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            txt=itemView.findViewById(R.id.view);
            price=itemView.findViewById(R.id.price);
            discount=itemView.findViewById(R.id.discount);

        }
    }
}
