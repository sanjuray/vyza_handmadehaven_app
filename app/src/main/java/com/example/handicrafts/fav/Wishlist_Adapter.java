package com.example.handicrafts.fav;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.handicrafts.R;
import com.example.handicrafts.detail.detail_view;

import java.util.ArrayList;

public class Wishlist_Adapter extends RecyclerView.Adapter<Wishlist_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Wishlist_Item> items;
    public Wishlist_Adapter(Context context, ArrayList<Wishlist_Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public Wishlist_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wishlist_item_layout,
                        parent,
                        false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Wishlist_Adapter.MyViewHolder holder, int position) {
        Wishlist_Item cur = items.get(position);

        Glide.with(context)
                .load(cur.getImages())
                .centerCrop()
                .error(R.drawable.punjab)
                .into(holder.product_image);

        holder.product_name.setText(cur.getName());
        String temp = "by "+cur.getState();
        holder.seller.setText(temp);
        // if there are going have rating stored then use switch or if-else ladder to set
        // ratings star by default five is used
        holder.ratings_stars.setImageResource(R.drawable.five_star_rating);
        holder.ratings_count.setText(R.string.no_ratings);
        float price = (float) (Float.parseFloat(cur.getPrice()) - 0.10 *(Float.parseFloat(cur.getPrice())));
        temp = "₹"+price;
        holder.pricing.setText(temp);
        temp = "M.R.P. "+cur.getPrice();
        holder.marked_price.setText(temp);
        temp = cur.getDiscount()+"% off";
        holder.deal_percent.setText(temp);

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context, detail_view.class);
            intent.putExtra("product_id", cur.getProduct_id());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        holder.close_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update in database by removing it from favourites
            }
        });

        holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add to cart on the user acc.
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageButton close_cancel;
        ImageView product_image;
        TextView product_name;
        TextView seller;
        ImageView ratings_stars;
        TextView ratings_count;
        TextView pricing;
        TextView marked_price;
        TextView deal_percent;
        Button add_to_cart;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            close_cancel = itemView.findViewById(R.id.close_cancel_shimmer);
            product_image = itemView.findViewById(R.id.product_image);
            product_name = itemView.findViewById(R.id.product_name_shimmer);
            seller = itemView.findViewById(R.id.seller_shimmer);
            ratings_stars = itemView.findViewById(R.id.rating_stars_shimmer);
            ratings_count = itemView.findViewById(R.id.ratings_count_shimmer);
            pricing = itemView.findViewById(R.id.pricing_shimmer);
            marked_price = itemView.findViewById(R.id.marked_price_shimmer);
            deal_percent = itemView.findViewById(R.id.deal_percent_shimmer);
            add_to_cart = itemView.findViewById(R.id.add_to_cart_shimmer);
        }
    }
}
