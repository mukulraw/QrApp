package com.genuinemark.qrapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Similarproducyt extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView grid;

    GridLayoutManager manager;

    GridAdapter adapter;

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

        toolbar.setTitle("Similar Product");

        grid = findViewById(R.id.grid);

        manager = new GridLayoutManager(this , 1);
        adapter = new GridAdapter(this);
        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);



    }

    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewAdapter> {



        Context context;

        public GridAdapter(Context context){

            this.context = context;
        }
        @NonNull
        @Override
        public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.grid_list_model , parent , false);
            return new MyViewAdapter(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewAdapter holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 15;
        }

        public class MyViewAdapter extends RecyclerView.ViewHolder {


            public MyViewAdapter(View itemView) {
                super(itemView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                });



            }
        }
    }




}
