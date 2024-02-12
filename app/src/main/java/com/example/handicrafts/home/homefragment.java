package com.example.handicrafts.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SearchView;
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

public class homefragment extends Fragment {
    SearchView searchView;
    ViewPager2 viewPager2;
    //use viewpager with a timer
    GridView gridView1;
    GridView gridview2;
    home_adapter adapter;
    ArrayList<home_data> data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_home, null);
        //searchView = view.findViewById(R.id.search);
        gridView1 = view.findViewById(R.id.grid_love);
        gridview2 = view.findViewById(R.id.grid2);
        //searchView.setQueryHint("Search Handicrafts Here :)");

        data = new ArrayList<>();
        adapter = new home_adapter(getContext(), data);
        gridView1.setAdapter(adapter);
        gridview2.setAdapter(adapter);
        fetchdata();
        return view;


    }

    private void fetchdata() {


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = "https://handmadehavens.com/database.php"; // Replace with your actual PHP API URL

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("YourTag", "Response: " + response.toString());
                        processJsonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("YourTag", "Error fetching data: " + error.toString());
                        // Handle errors
                        Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }

    private void processJsonResponse(JSONArray response) {
        try {
            data.clear();
            for (int i = 0; i < response.length(); i++) {
                JSONObject product = response.getJSONObject(i);

                home_data home_data = new home_data(
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

                //  viewData.setDiscount_logo(R.drawable.discount);
                home_data.setImages(product.getString("images"));
                home_data.setDiscount(product.getString("discount"));
                home_data.setName(product.getString("name"));
                home_data.setPrice(product.getString("price"));
                home_data.setDescription(product.getString(("description")));
                home_data.setState(product.getString("state"));
                home_data.setCity(product.getString("city"));
                home_data.setContent_review(product.getString("content_review"));

                //viewData.setFav(R.drawable.favourite);

                // viewData.setProduct_id(product.getString("product_id"));


                data.add(home_data);
            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}




