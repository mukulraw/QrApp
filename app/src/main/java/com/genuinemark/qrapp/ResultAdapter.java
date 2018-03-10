package com.genuinemark.qrapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewAdapter> {


    Context context;

    public ResultAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.grid1_list_model, parent, false);
        return new MyViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, int position) {





    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {

        TextView title , brand , quality , like;

        ImageView image;

        RatingBar ratingBar;

        public MyViewAdapter(View itemView) {
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
                    context.startActivity(intent);

                }
            });

        }
    }
}
