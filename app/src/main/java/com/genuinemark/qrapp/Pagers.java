package com.genuinemark.qrapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import me.relex.circleindicator.CircleIndicator;

public class Pagers extends AppCompatActivity {

    ViewPager pager;
    ViewPagerAdapter adapter;
    CircleIndicator indicator;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagers);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        toolbar.setTitle("Tutorial");


        pager = findViewById(R.id.pager);

        indicator = findViewById(R.id.indicator);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), 3);
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);




    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {


        public ViewPagerAdapter(FragmentManager fm, int list) {
            super(fm);


        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {

                return new Frag1();

            } else if (position == 1) {

                return new Frag2();
            } else if (position == 2) {

                return new Frag3();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


    public static class Frag1 extends Fragment {


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.frag1, container, false);
            return view;


        }
    }


    public static class Frag2 extends Fragment {


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.frag2, container, false);
            return view;


        }
    }

    public static class Frag3 extends Fragment {


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.frag3, container, false);
            return view;


        }
    }


}


