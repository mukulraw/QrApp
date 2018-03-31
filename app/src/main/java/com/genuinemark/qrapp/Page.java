package com.genuinemark.qrapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by USER on 01-03-2018.
 */

public class Page extends Fragment {

    ImageView image;

    Bitmap bmp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page, container, false);

        image = view.findViewById(R.id.image);

        final Bundle b = getArguments();
        String pa = b.getString("image");

        DisplayImageOptions options = new DisplayImageOptions.Builder().
                cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();

        final ImageLoader loader = ImageLoader.getInstance();

        loader.displayImage(pa , image , options);


        Log.d("image" , getArguments().getString("image"));

        /*loader.loadImage(getArguments().getString("image"), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                bmp = loadedImage;
                image.setImageBitmap(bmp);


            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });*/


        return view;
    }
}
