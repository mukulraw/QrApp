package com.genuinemark.qrapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.genuinemark.qrapp.ProductDetailsPOJO.Image;
import com.genuinemark.qrapp.ProductDetailsPOJO.ProductDetailsBean;
import com.genuinemark.qrapp.ProductDetailsPOJO.SimilarProduct;
import com.genuinemark.qrapp.ProductDetailsRequestPOJO.ProfileDetailRequestBean;
import com.genuinemark.qrapp.RateProductPOJO.RateProductBean;
import com.genuinemark.qrapp.RateProductRequestPOJO.RateRequestBean;
import com.genuinemark.qrapp.VerifyRequestPOJO.Data;
import com.genuinemark.qrapp.VerifyRequestPOJO.VerifyRequestBean;
import com.genuinemark.qrapp.verifyproductPOJO.VerifyProductBean;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import github.nisrulz.easydeviceinfo.base.EasyBatteryMod;
import github.nisrulz.easydeviceinfo.base.EasyDeviceMod;
import github.nisrulz.easydeviceinfo.base.EasyLocationMod;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ResultActivity extends AppCompatActivity {

    Toolbar toolbar;

    TextView click, click1 ;

    ImageView logo;

    RecyclerView grid;

    LinearLayoutManager manager;

    ResultAdapter adapter1;

    RelativeLayout gray, green , red;

    ImageView tick;

    TextView product, info, brand, quality, manufacture, title;

    CircleIndicator indicator;

    ViewPager pager;

    SimpleExoPlayer player;

    RatingBar rating;

    ProgressBar bar;

    List<SimilarProduct> list;

    List<Image> list1;

    String s;

    LinearLayout arrow;

    SharedPreferences pref;

    SharedPreferences.Editor edi;

    String qrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        edi = pref.edit();

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(ResultActivity.this));

        toolbar = findViewById(R.id.toolbar);

        click = findViewById(R.id.click);

        grid = findViewById(R.id.grid);

        green = findViewById(R.id.green);

        gray = findViewById(R.id.gray);

        tick = findViewById(R.id.tick);

        product = findViewById(R.id.product);

        info = findViewById(R.id.info);

        pager = findViewById(R.id.pager);

        logo = findViewById(R.id.logo);

        brand = findViewById(R.id.brand);

        quality = findViewById(R.id.quality);

        red = findViewById(R.id.red);

        manufacture = findViewById(R.id.manufacture);

        title = findViewById(R.id.title);

        logo = findViewById(R.id.logo);

        rating = findViewById(R.id.rating);

        bar = findViewById(R.id.progress);

        arrow = findViewById(R.id.arrow);

        indicator = (CircleIndicator) findViewById(R.id.indicator);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.BLACK);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        list = new ArrayList<>();

        adapter1 = new ResultAdapter(this, list);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter1);

        list1 = new ArrayList<>();


        final SimpleExoPlayerView simpleExoPlayerView = findViewById(R.id.player);

        simpleExoPlayerView.setPlayer(player);

        simpleExoPlayerView.setUseController(false);

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

        qrid = getIntent().getStringExtra("data");

        data.setQrId(getIntent().getStringExtra("data"));

        Log.d("nisha", getIntent().getStringExtra("data"));

        Log.d("kamla", pref.getString("userId", ""));

        data.setUserId(pref.getString("userId", ""));

        pro.setData(data);

        Call<ProductDetailsBean> call = all.productdetailsbean(pro);

        call.enqueue(new Callback<ProductDetailsBean>() {
            @Override
            public void onResponse(Call<ProductDetailsBean> call, Response<ProductDetailsBean> response) {

                if (Objects.equals(response.body().getStatus(), "1")) {

                    adapter1.setgrid(response.body().getData().getSimilarProducts());

                    final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), response.body().getData().getImages());

                    pager.setAdapter(adapter);

                    indicator.setViewPager(pager);

                    brand.setText(response.body().getData().getBrandName());

                    quality.setText(response.body().getData().getQuality());

                    manufacture.setText(response.body().getData().getManufacturingDate());

                    title.setText(response.body().getData().getProductName());

                    rating.setRating(Float.parseFloat(response.body().getData().getRating()));

                    s = response.body().getData().getBrandId();

                    //Toast.makeText(ResultActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true)
                            .resetViewBeforeLoading(false).build();

                    ImageLoader loader = ImageLoader.getInstance();

                    loader.displayImage(response.body().getData().getCompanyLogo(), logo, options);

                    if (Objects.equals(response.body().getData().getVerificationStatus(), "verified")) {


                        green.setVisibility(View.VISIBLE);
                        gray.setVisibility(View.GONE);
                        red.setVisibility(View.GONE);


                    }else  if (Objects.equals(response.body().getData().getVerificationStatus(), "Fake")){

                        green.setVisibility(View.GONE);
                        gray.setVisibility(View.GONE);
                        red.setVisibility(View.VISIBLE);


                    }

                    else {

                        green.setVisibility(View.GONE);
                        gray.setVisibility(View.VISIBLE);
                        red.setVisibility(View.GONE);

                    }


                     if (Objects.equals(response.body().getData().getIsVideoAvailable() , "1")){

                        simpleExoPlayerView.setVisibility(View.VISIBLE);

                        //Toast.makeText(ResultActivity.this, response.body().getData().getIsVideoAvailable(), Toast.LENGTH_SHORT).show();

                    }
                    else {

                         simpleExoPlayerView.setVisibility(View.GONE);
                     }


                    if (Objects.equals(response.body().getData().getIsRated(), "TRUE")) {

                        arrow.setVisibility(View.GONE);

                    }

                    else {

                        arrow.setVisibility(View.VISIBLE);

                    }

                } else {

                    Toast.makeText(ResultActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }


                bar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ProductDetailsBean> call, Throwable t) {


                bar.setVisibility(View.GONE);

            }
        });


        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(ResultActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.ratingdialog);
                dialog.setCancelable(true);
                dialog.show();

                final RatingBar ratingBar = dialog.findViewById(R.id.rating);
                final EditText comment = dialog.findViewById(R.id.comment);
                Button submit = dialog.findViewById(R.id.Submit);

                final ProgressBar bar = dialog.findViewById(R.id.progress);


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        bar.setVisibility(View.VISIBLE);

                        Bean b = (Bean) getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseurl)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ApiAInterface all = retrofit.create(ApiAInterface.class);

                        RateRequestBean r = new RateRequestBean();

                        r.setAction("rate_product");

                        com.genuinemark.qrapp.RateProductRequestPOJO.Data d = new com.genuinemark.qrapp.RateProductRequestPOJO.Data();

                        d.setComment(comment.getText().toString());
                        d.setRating(String.valueOf(ratingBar.getRating()));
                        d.setUserId(pref.getString("userId", ""));
                        d.setQrId(getIntent().getStringExtra("data"));

                        Log.d("dajfklsd", d.getUserId());

                        r.setData(d);


                        Call<RateProductBean> call = all.req(r);
                        call.enqueue(new Callback<RateProductBean>() {
                            @Override
                            public void onResponse(Call<RateProductBean> call, Response<RateProductBean> response) {


                                Toast.makeText(ResultActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                dialog.dismiss();
                                bar.setVisibility(View.GONE);
                            }


                            @Override
                            public void onFailure(Call<RateProductBean> call, Throwable t) {
                                bar.setVisibility(View.GONE);

                            }
                        });


                    }
                });


            }
        });



       /* BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);

        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        player =
                ExoPlayerFactory.newSimpleInstance(ResultActivity.this, trackSelector);

        rtmpDataSourceFactory = new RtmpDataSourceFactory();

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(list.get(position).getVideo()),
                new DefaultHttpDataSourceFactory("exoplayer-codelab"), extractorsFactory, null, null);

        //Log.d("hdfjkhsdf", list.get(position).getVideo());


*/

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ResultActivity.this, Similarproducyt.class);
                i.putExtra("brand", s);
                startActivity(i);


            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ResultActivity.this, productDetails.class);
                i.putExtra("q", qrid);
                startActivity(i);


            }
        });


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(ResultActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                dialog.setContentView(R.layout.dialog);

                dialog.setCancelable(true);

                dialog.show();

                Button submit = dialog.findViewById(R.id.submit);

                final EditText code = dialog.findViewById(R.id.code);

                final ProgressBar bar = dialog.findViewById(R.id.progress);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String c = code.getText().toString();

                        if (c.length() > 0) {

                            Log.d("code", code.getText().toString());

                            EasyDeviceMod easyDeviceMod = new EasyDeviceMod(ResultActivity.this);

                            EasyLocationMod easyLocationMod = new EasyLocationMod(ResultActivity.this);

                            EasyBatteryMod easyBatteryMod = new EasyBatteryMod(ResultActivity.this);


                            if (ActivityCompat.checkSelfPermission(ResultActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                                return;
                            }


                            String imei = easyDeviceMod.getIMEI();

                            double[] l = easyLocationMod.getLatLong();
                            String lat = String.valueOf(l[0]);
                            String lon = String.valueOf(l[1]);

                            final int battery = easyBatteryMod.getBatteryPercentage();

                            bar.setVisibility(View.VISIBLE);

                            Bean b = (Bean) getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseurl)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            ApiAInterface all = retrofit.create(ApiAInterface.class);

                            VerifyRequestBean verify = new VerifyRequestBean();

                            verify.setAction("verify_product");

                            Data data = new Data();

                            data.setBattery(String.valueOf(battery));
                            data.setImei(imei);
                            data.setLatitude(lat);
                            data.setLatitude(lon);
                            data.setQrId(getIntent().getStringExtra("data"));
                            data.setUserId(pref.getString("userId", ""));
                            data.setVerificationCode(code.getText().toString());

                            verify.setData(data);

                            Log.d("imei", imei);
                            Log.d("lat", lat);
                            Log.d("lon", lon);

                            Call<VerifyProductBean> call = all.verifybean(verify);

                            call.enqueue(new Callback<VerifyProductBean>() {
                                @Override
                                public void onResponse(Call<VerifyProductBean> call, Response<VerifyProductBean> response) {

                                    if (Objects.equals(response.body().getStatus(), "1")) {

                                        Toast.makeText(ResultActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        green.setVisibility(View.VISIBLE);
                                        tick.setVisibility(View.VISIBLE);
                                        dialog.dismiss();

                                    } else {
                                        Toast.makeText(ResultActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    Log.d("response", "response");

                                    bar.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<VerifyProductBean> call, Throwable t) {

                                    Log.d("failure", t.toString());

                                    bar.setVisibility(View.GONE);

                                }
                            });


                        } else {
                            Toast.makeText(ResultActivity.this, "Please enter a Verification code", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


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

    public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewAdapter> {

        Context context;

        List<SimilarProduct> list = new ArrayList<>();

        public ResultAdapter(Context context, List<SimilarProduct> list) {

            this.context = context;
            this.list = list;

        }

        @NonNull
        @Override
        public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.grid1_list_model, parent, false);
            return new MyViewAdapter(view);

        }

        @Override
        public void onBindViewHolder(MyViewAdapter holder, int position) {

            SimilarProduct item = list.get(position);

            holder.title.setText(item.getProductName());
            holder.brand.setText(item.getBrandName());
            holder.quality.setText(item.getQuality());

            try {
                holder.ratingBar.setRating(Float.parseFloat(item.getRating()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            holder.like.setText(item.getRecommendation());


            DisplayImageOptions options = new DisplayImageOptions.Builder().
                    cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();

            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(item.getProductImage(), holder.image, options);


        }

        public void setgrid(List<SimilarProduct> list) {

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewAdapter extends RecyclerView.ViewHolder {

            TextView title, brand, quality, like;

            ImageView image;

            RatingBar ratingBar;

            public MyViewAdapter(final View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);

                brand = itemView.findViewById(R.id.brand);

                quality = itemView.findViewById(R.id.quality);

                like = itemView.findViewById(R.id.like);

                image = itemView.findViewById(R.id.image);

                ratingBar = itemView.findViewById(R.id.points);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(context, productDetails.class);

                        intent.putExtra("q", qrid);

                        context.startActivity(intent);

                    }
                });

            }
        }
    }


}
