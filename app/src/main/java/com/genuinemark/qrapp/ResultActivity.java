package com.genuinemark.qrapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;

public class ResultActivity extends AppCompatActivity {

    Toolbar toolbar;

    Button click, click1;

    RecyclerView grid;

    LinearLayoutManager manager;

    ResultAdapter adapter;

    RelativeLayout gray, green;

    ImageView tick;

    TextView product, info;

    CircleIndicator indicator;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        toolbar = findViewById(R.id.toolbar);

        click = findViewById(R.id.click);

        grid = findViewById(R.id.grid);

        green = findViewById(R.id.green);

        gray = findViewById(R.id.gray);

        tick = findViewById(R.id.tick);

        product = findViewById(R.id.product);

        info = findViewById(R.id.info);

        pager = findViewById(R.id.pager);

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

        adapter = new ResultAdapter(this);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

        indicator.setViewPager(pager);

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ResultActivity.this, Similarproducyt.class);
                startActivity(i);

            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(ResultActivity.this, productDetails.class);
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
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        green.setVisibility(View.VISIBLE);
                        tick.setVisibility(View.VISIBLE);

                        dialog.dismiss();
                    }
                });


            }
        });


    }


    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new Page();
        }

        @Override
        public int getCount() {
            return 5;
        }
    }


}
