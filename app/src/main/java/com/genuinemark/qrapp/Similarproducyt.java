package com.genuinemark.qrapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.genuinemark.qrapp.ProductDetailsPOJO.ProductDetailsBean;
import com.genuinemark.qrapp.RegisterPOJO.RegiserBean;
import com.genuinemark.qrapp.SimilarProductPOJO.Datum;
import com.genuinemark.qrapp.SimilarProductPOJO.SimilarProductBean;
import com.genuinemark.qrapp.SimilarProductRequestPOJO.Data;
import com.genuinemark.qrapp.SimilarProductRequestPOJO.SimilarRequestBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Similarproducyt extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView grid;

    GridLayoutManager manager;

    GridAdapter adapter;

    List<Datum> list;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similarproducyt);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setTitle("Similar Products");

        grid = findViewById(R.id.grid);

        bar = findViewById(R.id.progress);

        manager = new GridLayoutManager(this, 1);

        list = new ArrayList<>();

        adapter = new GridAdapter(this, list);

        grid.setAdapter(adapter);

        grid.setLayoutManager(manager);

        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiAInterface all = retrofit.create(ApiAInterface.class);

        SimilarRequestBean simi = new SimilarRequestBean();

        final Data data = new Data();

        simi.setAction("similar_products");

        data.setBrandId(getIntent().getStringExtra("brand"));
        simi.setData(data);

        Gson gson = new GsonBuilder().create();
        String payloadStr = gson.toJson(simi);
        Log.d("nisha", payloadStr);

        Call<SimilarProductBean> call = all.similar(simi);

        call.enqueue(new Callback<SimilarProductBean>() {
            @Override
            public void onResponse(Call<SimilarProductBean> call, Response<SimilarProductBean> response) {

                try {
                    if (Objects.equals(response.body().getStatus(), "1")) {

                        adapter.setgrid(response.body().getData());

                        Log.d("kamal", String.valueOf(response.body().getData().size()));

                        Toast.makeText(Similarproducyt.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(Similarproducyt.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }



                bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<SimilarProductBean> call, Throwable t) {

                Log.d("manisha", t.toString());

                bar.setVisibility(View.GONE);

            }
        });


    }

    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewAdapter> {


        Context context;

        List<Datum> list = new ArrayList<>();

        public GridAdapter(Context context, List<Datum> list) {

            this.context = context;

            this.list = list;
        }

        @NonNull
        @Override
        public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.grid2_list_model, parent, false);
            return new MyViewAdapter(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewAdapter holder, int position) {

            Datum item = list.get(position);


            try {

                holder.quality.setText(item.getQuality());
                holder.like.setText(item.getRecommendation());
                holder.title.setText(item.getProductName());
                holder.brand.setText(item.getBrandName());
                holder.bar.setRating(Float.parseFloat(item.getRating()));


            }catch (Exception e){

                e.printStackTrace();
            }

            Log.d("quality", item.getQuality());
            Log.d("like", item.getRecommendation());
            Log.d("title", item.getProductName());
            Log.d("brand", item.getBrandName());


            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage(item.getBrandLogo(), holder.logo, options);


            DisplayImageOptions options1 = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();

            ImageLoader loader1 = ImageLoader.getInstance();

            loader1.displayImage(item.getProductImage(), holder.image, options1);

         /*   holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();

                }
            });
*/

        }

        public void setgrid(List<Datum> list) {


            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewAdapter extends RecyclerView.ViewHolder {


            ImageView logo, image;

            TextView title, brand, quality, like;

            RatingBar bar;


            public MyViewAdapter(View itemView) {
                super(itemView);

                brand = (TextView) itemView.findViewById(R.id.brand);

                like = (TextView) itemView.findViewById(R.id.like);

                quality = (TextView) itemView.findViewById(R.id.quality);

                title = (TextView) itemView.findViewById(R.id.title);

                image = itemView.findViewById(R.id.image);

                logo = itemView.findViewById(R.id.logo);

                bar = itemView.findViewById(R.id.rating);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent i = new Intent(context , productDetails.class);
                        i.putExtra("q" , list.get(getAdapterPosition()).getQrId());
                        context.startActivity(i);
                    }
                });


            }
        }
    }


}
