package com.genuinemark.qrapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by USER on 01-03-2018.
 */

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, productDetails.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {


        public MyViewAdapter(View itemView) {
            super(itemView);


        }
    }
}
