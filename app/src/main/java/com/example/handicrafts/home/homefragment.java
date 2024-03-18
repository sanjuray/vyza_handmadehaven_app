package com.example.handicrafts.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.handicrafts.R;
import com.facebook.shimmer.ShimmerFrameLayout;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class homefragment extends Fragment {
    RelativeLayout relativeLayout;
    SearchView searchView;
    ViewPager2 viewPager2;
    //use viewpager with a timer
    GridView gridview2;
    RecyclerView banner;
    RecyclerView recyclerView,customer;
    RecyclerView recyclerView2;
    customer_adapter customer_adapter;
    recycler_adapter adapter2;
    //String url=https://handmadehavens.com/review.php


     ArrayList<home_data> filteredData;
    ArrayList<home_data> data;
    ArrayList<data> datalist;
    int[] images = {R.drawable.hand1,R.drawable.hand2, R.drawable.hand3,R.drawable.hand2};
     int currentPage = 0;
     ShimmerFrameLayout frameLayout;
     LottieAnimationView animationView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_home, null);
        if (!isConnected(getContext())) {
            showNoInternetDialog();
        }
        animationView=view.findViewById(R.id.no_internet);
        relativeLayout=view.findViewById(R.id.relative9);
        searchView = view.findViewById(R.id.search);
        frameLayout=view.findViewById(R.id.shimms);
        //this line is important
        datalist=new ArrayList<>();
        customer=view.findViewById(R.id.reviews);
        customer_adapter=new customer_adapter(getContext(),datalist);
        customer.setAdapter(customer_adapter);
        customer.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        review();



        banner=view.findViewById(R.id.banner);
        recycler_banner banner2=new recycler_banner(getContext(),images);
        banner.setAdapter(banner2);
        banner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


  Handler handler=new Handler(Looper.getMainLooper());

       Timer timer=new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage == images.length) {
                            currentPage = 0;
                        }
                        banner.smoothScrollToPosition(currentPage++);
                    }
                });
            }
        };


        timer.schedule(timerTask, 1000, 3000);





        recyclerView2=view.findViewById(R.id.grid2);
        recyclerView=view.findViewById(R.id.grid_love);
        searchView.setQueryHint("Search Handicrafts Here :)");
        searchView.getSolidColor();
        searchView.clearFocus();
        EditText txtSearch = ((EditText)searchView.findViewById(androidx.appcompat.R.id.search_src_text));

        txtSearch.setHintTextColor(getResources().getColor(R.color.black));
        txtSearch.setTextColor(Color.BLACK);


        data = new ArrayList<>();
        adapter2=new recycler_adapter(getContext(),data);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter2);
        GridLayoutManager layoutManager2 = new GridLayoutManager(getContext(), 2);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(adapter2);

        //adapter = new home_adapter(getContext(), data);

        //gridview2.setAdapter(adapter);

        fetchdata();

        filteredData = new ArrayList<>(data);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               // banner.setVisibility(View.INVISIBLE);
                filter(newText);
                return true;
            }
        });




        return view;


    }

    private void showNoInternetDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());


        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.no_internet, null);
        animationView=dialogView.findViewById(R.id.no_internet);
        animationView.setAnimation(R.raw.internet);
        animationView.playAnimation();
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();


        dialog.show();
    }

    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }


    private void review() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String urls = "https://handmadehavens.com/review.php";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, urls, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                reviews(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // here we will send that pls check you internet connection
            }
        }
        );
        requestQueue.add(arrayRequest);


    }

    private void reviews(JSONArray response) {
        try{
            datalist.clear();
            for(int i=0;i<response.length();i++){
                JSONObject object=response.getJSONObject(i);
                data data1=new data(
                        object.getString("user_name"),
                         object.getString("user_review"),
                        object.getInt("user_rating"),
                        object.getInt("review_id")
                );

                data1.setUser_review(object.getString("user_review"));
                data1.setUser_name(object.getString("user_name"));
                data1.setUser_rating(object.getInt("user_rating"));
                datalist.add(data1);
            }
            customer_adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //

    }

    private void filter(String newText) {

        ArrayList<home_data> filteredlist=new ArrayList<>();
        for (home_data item:data){
            if (item.getName().toLowerCase().contains(newText.toLowerCase())){
                filteredlist.add(item);
            }
        }
        adapter2.filterlist(filteredlist);
    }


    private void fetchdata() {
        frameLayout.startShimmerAnimation();


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
                frameLayout.postDelayed(() -> {
                    frameLayout.stopShimmerAnimation();
                    frameLayout.setVisibility(View.GONE);
                    recyclerView2.setVisibility(View.VISIBLE);
                }, 3000);
            }
            //adapter.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}




