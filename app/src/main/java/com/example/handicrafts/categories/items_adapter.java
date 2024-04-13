package com.example.handicrafts.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.handicrafts.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class items_adapter extends RecyclerView.Adapter<items_adapter.viewholder> {

    grid_adapter adapter;

    ArrayList<state_data> dataArrayList;
    ArrayList<data> newdata;
    Context context;
    boolean isclosed=false;

    public items_adapter(ArrayList<state_data> dataArrayList, Context context) {
        this.dataArrayList = dataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        state_data data=dataArrayList.get(position);
        holder.textView.setText(data.getName());
        Glide.with(context).load(data.getDrop()).into(holder.imageView);
        Glide.with(context).load(data.getImage()).into(holder.imageViews);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isclosed) {//is not true
                    holder.view.setVisibility(View.VISIBLE);
                    holder.imageView.setImageResource(R.drawable.baseline_arrow_drop_up_24);
                    isclosed = true;
                    newdata=new ArrayList<>();

                    switch (holder.getAdapterPosition()){
                        case 0 :
                        arunachal();

                        case 1:
                            assam();

                        case 2:
                            kerala();

                        case 3:
                            rajasthan();

                        case 4:
                            uttrakhand();

                        case 5:
                            tamil_nadu();

                        case 6:
                            odisha();

                        case 7 :
                            andhra();
                    }




                    adapter=new grid_adapter(newdata, view.getContext());


                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
                    holder.view.setLayoutManager(layoutManager);
                    holder.view.setAdapter(adapter);

                }
                else {
                    holder.view.setVisibility(View.GONE);
                    holder.imageView.setImageResource(R.drawable.baseline_arrow_drop_down_24);
                    isclosed=false;
                }



            }


        });

    }

    private void andhra() {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String url="https://handmadehavens.com/testing.php?state=Andhra Pradesh";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                productdata(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "App is great and so are you ", Toast.LENGTH_SHORT).show();

            }
        }

        );
        requestQueue.add(arrayRequest);
    }

    // could have passed parameters as state names to url and code rendundancy would have reduced but now wories will take care in future
    private void odisha() {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String url="https://handmadehavens.com/testing.php?state=Tamil Nadu";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                productdata(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "App is great and so are you ", Toast.LENGTH_SHORT).show();

            }
        }

        );
        requestQueue.add(arrayRequest);
    }

    private void tamil_nadu() {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String url="https://handmadehavens.com/testing.php?state=Tamil Nadu";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                productdata(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "App is great and so are you ", Toast.LENGTH_SHORT).show();

            }
        }

        );
        requestQueue.add(arrayRequest);
    }

    private void uttrakhand() {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String url="https://handmadehavens.com/testing.php?state=uttarakhand";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                productdata(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "App is great and so are you ", Toast.LENGTH_SHORT).show();

            }
        }

        );
        requestQueue.add(arrayRequest);

    }


    private void rajasthan() {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String url="https://handmadehavens.com/testing.php?state=rajasthan";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                productdata(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "App is great and so are you ", Toast.LENGTH_SHORT).show();

            }
        }

        );
        requestQueue.add(arrayRequest);

    }



    private void kerala() {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String url="https://handmadehavens.com/testing.php?state=kerala";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                productdata(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "App is great and so are you ", Toast.LENGTH_SHORT).show();

            }
        }

        );
        requestQueue.add(arrayRequest);

    }


    private void assam() {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String url="https://handmadehavens.com/testing.php?state=Bihar";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                productdata(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "App is great and so are you ", Toast.LENGTH_SHORT).show();

            }
        }

        );
        requestQueue.add(arrayRequest);

    }

    private void arunachal() {
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        String url="https://handmadehavens.com/testing.php?state=Andhra pradesh";
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                productdata(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "App is great and so are you ", Toast.LENGTH_SHORT).show();

            }
        }

        );
        requestQueue.add(arrayRequest);
    }

    private void productdata(JSONArray response) {

        try {
            newdata.clear();
            for(int i=0;i<response.length();i++){
                JSONObject OBJECT= response.getJSONObject(i);

                    data data=new data(
                            OBJECT.getString("product_id"),
                            OBJECT.getString("images"),
                            OBJECT.getString("name"),
                            OBJECT.getString("price"),
                            OBJECT.getString("discount"),
                            OBJECT.getString("description"),
                            OBJECT.getString("content_review"),
                            OBJECT.getString("state"),
                            OBJECT.getString("city"),
                            R.drawable.favourite,
                            R.drawable.discount




                    );
                    data.setCity(OBJECT.getString("city"));
                    data.setName(OBJECT.getString("name"));
                    data.setImages(OBJECT.getString("images"));
                    //data.setCity(OBJECT.getString("city"));
                    data.setPrice(OBJECT.getString("price"));
                    data.setDiscount(OBJECT.getString("discount"));
                    data.setContent_review(OBJECT.getString("content_review"));

                    newdata.add(data);
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }




    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public static class viewholder extends RecyclerView.ViewHolder{

        RecyclerView view;
        CircleImageView imageViews;
        ImageView imageView;
        TextView textView;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            view=itemView.findViewById(R.id.views);
            textView=itemView.findViewById(R.id.state_name);
            imageView=itemView.findViewById(R.id.drop2);
            imageViews=itemView.findViewById(R.id.circle);

        }
    }
}
