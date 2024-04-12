package com.example.handicrafts.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.handicrafts.R;
import com.example.handicrafts.login.LoginPage;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class accountFragment extends Fragment {
    ShimmerFrameLayout shimmerFrameLayout;
    TextView textView;
    TextView name;
    TextView addresses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.account,null);
        textView=view.findViewById(R.id.accounts);
        addresses=view.findViewById(R.id.address);
        name=view.findViewById(R.id.username);
        shimmerFrameLayout=view.findViewById(R.id.shimmer);

        SharedPreferences sharedPreferences= requireContext().getSharedPreferences("share", Context.MODE_PRIVATE);
        String uname=sharedPreferences.getString("name","");
        name.setText(uname);
        getall();


       // shimmerFrameLayout.startShimmerAnimation();
        ListView listView=view.findViewById(R.id.list_items);
        listView.setVisibility(View.GONE);
        ArrayList<model> models = new ArrayList<>();
        models.add(new model(R.drawable.baseline_business_24, "Edit profile",R.drawable.baseline_arrow_forward_24));
        models.add(new model(R.drawable.baseline_add_home_24, "Address",R.drawable.baseline_arrow_forward_24));
        models.add(new model(R.drawable.baseline_phone_24, "Phone number",R.drawable.baseline_arrow_forward_24));
        models.add(new model(R.drawable.baseline_contact_support_24, "Contact us",R.drawable.baseline_arrow_forward_24));
        models.add(new model(R.drawable.baseline_rate_review_24, "Rate us",R.drawable.baseline_arrow_forward_24));
        models.add(new model(R.drawable.baseline_bookmark_border_24, "Orders",R.drawable.baseline_arrow_forward_24));
        models.add(new model(R.drawable.baseline_logout_24, "Log out",R.drawable.baseline_arrow_forward_24));

        profile_adapter adapter = new profile_adapter(getContext(), models);


        listView.setAdapter(adapter);
        shimmerFrameLayout.postDelayed(() -> {
            shimmerFrameLayout.stopShimmerAnimation();
            shimmerFrameLayout.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }, 3000);

       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             click();
           }

       });

       listView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              logout_app();
           }
       });

       addresses.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               addresses1();
           }
       });

        return view;




    }

    private void logout_app() {
        Intent intent=new Intent(getActivity(), LoginPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        SharedPreferences preferences= getActivity().getSharedPreferences("MyPrefs",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putInt("userId",-1);
        editor.commit();
    }

    public void getall() {
        String url="https://handmadehavens.com/getall.php";
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                fetch(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    public void fetch(JSONObject response) {
        try {
            String name=response.getString("name");
            String city=response.getString("city");
            String state=response.getString("state");
            String pincode=response.getString("pincode");
            SharedPreferences sharedPreferences= requireContext().getSharedPreferences("share",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("name",name);
            editor.putString("city",city);
            editor.putString("state",state);
            editor.putString("pincode",pincode);
            editor.commit();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }



    private void addresses1() {
        Intent intent=new Intent(getContext(), detail_activity.class);
        startActivity(intent);
    }

    private void click() {
        Intent intent=new Intent(getContext(), edit_profile.class);
        startActivity(intent);
    }

     @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        shimmerFrameLayout.stopShimmerAnimation();
        super.onPause();
    }
}
