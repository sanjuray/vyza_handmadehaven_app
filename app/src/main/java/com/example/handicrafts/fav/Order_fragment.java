package com.example.handicrafts.fav;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.handicrafts.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Order_fragment extends Fragment {

    //the fav. items
    ArrayList<Wishlist_Item> items;
    //recycler view in favs page
    RecyclerView wishlist_items_view;
    Wishlist_Adapter wishlistAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wishlist_fragment_layout, null);

        if (!isConnected(getContext())) {
            showNoInternetDialog();
        }else{
            items = new ArrayList<>();
            wishlistAdapter = new Wishlist_Adapter(getContext(),items);
            wishlist_items_view.setLayoutManager(new LinearLayoutManager(getContext()));
            wishlist_items_view.setAdapter(wishlistAdapter);
            getFavoriteItems();

        }

        return view;
    }

    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }

    private void showNoInternetDialog() {
        //make ur custom no internet dog like amzn
    }

    private void getFavoriteItems() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "https://handmadehavens.com/get_favourites.php"; // Replace with your actual PHP API URL

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("GET", "Response: " + response.toString());
                        refineResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("GET_ERROR", "Error fetching data: " + error.toString());
                        // Handle errors
                        Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    private void refineResponse(JSONArray response) {

        try {
            items.clear();

            for (int i = 0; i < response.length(); i++) {
                JSONObject product = response.getJSONObject(i);

                Wishlist_Item item = new Wishlist_Item(
                        product.getString("product_id"),
                        product.getString("images"),
                        product.getString("name"),
                        product.getString("price"),
                        product.getString("discount"),
                        product.getString("description"),
                        product.getString("content_review"),
                        product.getString("state"),
                        product.getString("city"),
                        R.drawable.favorite2,
                        R.drawable.discount
                );

                item.setImages(product.getString("images"));
                item.setDiscount(product.getString("discount"));
                item.setName(product.getString("name"));
                item.setPrice(product.getString("price"));
                item.setDescription(product.getString(("description")));
                item.setState(product.getString("state"));
                item.setCity(product.getString("city"));
                item.setContent_review(product.getString("content_review"));

                items.add(item);
            }

            wishlistAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}