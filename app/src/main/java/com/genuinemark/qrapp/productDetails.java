package com.genuinemark.qrapp;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import at.blogc.android.views.ExpandableTextView;
import me.relex.circleindicator.CircleIndicator;

public class productDetails extends AppCompatActivity {

    Toolbar toolbar;

    ViewPager pager;

    CircleIndicator indicator;

    ExpandableTextView expandableTextView;

    TextView click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        toolbar = findViewById(R.id.toolbar);

        pager = findViewById(R.id.pager);

        indicator = findViewById(R.id.indicator);

        expandableTextView = findViewById(R.id.view);

        click = findViewById(R.id.click);

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

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

        indicator.setViewPager(pager);

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
