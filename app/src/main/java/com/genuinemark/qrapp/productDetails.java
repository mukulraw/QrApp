package com.genuinemark.qrapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.genuinemark.qrapp.ProductDetailsPOJO.Image;
import com.genuinemark.qrapp.ProductDetailsPOJO.ProductDetailsBean;
import com.genuinemark.qrapp.ProductDetailsRequestPOJO.ProfileDetailRequestBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import at.blogc.android.views.ExpandableTextView;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class productDetails extends AppCompatActivity {

    Toolbar toolbar;

    ViewPager pager;

    CircleIndicator indicator;

    ExpandableTextView expandableTextView;

    TextView click, title, brand, quality, info, like;

    ImageView nike;

    ProgressBar bar;

    SharedPreferences pref;

    SharedPreferences.Editor edit;

    RatingBar rating;

    List<Image> list1;

    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        edit = pref.edit();

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(productDetails.this));

        toolbar = findViewById(R.id.toolbar);

        bar = findViewById(R.id.progress);

        rating = findViewById(R.id.rating);

        pager = findViewById(R.id.pager);

        indicator = findViewById(R.id.indicator);

        expandableTextView = findViewById(R.id.view);

        click = findViewById(R.id.click);

        title = findViewById(R.id.title);

        brand = findViewById(R.id.brand);

        quality = findViewById(R.id.quality);

        like = findViewById(R.id.like);

        info = findViewById(R.id.info);

        nike = findViewById(R.id.image);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.BLACK);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        toolbar.setTitle("Product Details");

        list1 = new ArrayList<>();

        expandableTextView.setAnimationDuration(750L);

        expandableTextView.setInterpolator(new OvershootInterpolator());

        expandableTextView.setExpandInterpolator(new OvershootInterpolator());

        expandableTextView.setCollapseInterpolator(new OvershootInterpolator());


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {

                //expandableTextView.expand();
                expandableTextView.toggle();

            }
        });

        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiAInterface all = retrofit.create(ApiAInterface.class);

        ProfileDetailRequestBean pro = new ProfileDetailRequestBean();

        pro.setAction("product_details");

        com.genuinemark.qrapp.ProductDetailsRequestPOJO.Data data = new com.genuinemark.qrapp.ProductDetailsRequestPOJO.Data();

        data.setQrId(getIntent().getStringExtra("q"));


        // Log.d("nisha" , getIntent().getStringExtra("data"));

        //Log.d("kamla" ,pref.getString("userId" , ""));
        data.setUserId(pref.getString("userId", ""));

        pro.setData(data);

        Call<ProductDetailsBean> call = all.productdetailsbean(pro);

        call.enqueue(new Callback<ProductDetailsBean>() {
            @Override
            public void onResponse(Call<ProductDetailsBean> call, Response<ProductDetailsBean> response) {

                try {

                    if (Objects.equals(response.body().getStatus(), "1")) {

                        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), response.body().getData().getImages());

                        pager.setAdapter(adapter);

                        indicator.setViewPager(pager);

                        brand.setText(response.body().getData().getBrandName());

                        quality.setText(response.body().getData().getQuality());

                        info.setText(response.body().getData().getInfo());

                        Log.d("info", response.body().getData().getInfo());

                        like.setText(response.body().getData().getRecommendation());

                        title.setText(response.body().getData().getProductName());

                        expandableTextView.setText(Html.fromHtml(response.body().getData().getDescription()));

                        rating.setRating(Float.parseFloat(response.body().getData().getRating()));

                        s = response.body().getData().getBrandId();

                        //Toast.makeText(productDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true)
                                .resetViewBeforeLoading(false).build();

                        ImageLoader loader = ImageLoader.getInstance();

                        loader.displayImage(response.body().getData().getBrandLogo(), nike, options);

                    } else {
                        Toast.makeText(productDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }catch (Exception e){

                    e.printStackTrace();
                }


                bar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ProductDetailsBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });


    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        List<Image> list1 = new ArrayList<>();

        public PagerAdapter(FragmentManager fm, List<Image> list1) {

            super(fm);
            this.list1 = list1;
        }

        @Override
        public Fragment getItem(int position) {

            Page page = new Page();
            Bundle b = new Bundle();
            b.putString("image", list1.get(position).getUrl());
            page.setArguments(b);
            return page;
        }

        @Override
        public int getCount() {
            return list1.size();
        }
    }

}
