package com.example.handicrafts.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.handicrafts.R;

import java.util.ArrayList;

public class customer_adapter extends RecyclerView.Adapter<customer_adapter.viewholder> {
    Context context;
    ArrayList<data> dataArrayList;

    public customer_adapter(Context context, ArrayList<data> dataArrayList) {
        this.context = context;
        this.dataArrayList = dataArrayList;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.review_customer,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        data data=dataArrayList.get(position);
        holder.review.setText(data.getUser_review());
        holder.bar.setRating(data.getUser_rating());
        holder.name.setText(data.getUser_name());


    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        RatingBar bar;
        TextView name,review;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            bar=itemView.findViewById(R.id.rating);
            name=itemView.findViewById(R.id.name);
            review=itemView.findViewById(R.id.review);
        }
    }
}
