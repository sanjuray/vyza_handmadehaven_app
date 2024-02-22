package com.example.handicrafts.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.handicrafts.R;

public class recycler_banner extends RecyclerView.Adapter<recycler_banner.viewholder> {
    Context context;
    int[] images;

    public recycler_banner(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Glide.with(context).load(images[position]).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imagebanner);


        }
    }
}
