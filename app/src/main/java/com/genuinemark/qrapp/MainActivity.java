package com.genuinemark.qrapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PointF;
import android.hardware.Camera;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.genuinemark.qrapp.RateProductPOJO.Image;
import com.genuinemark.qrapp.locationRequestPOJO.Data;
import com.genuinemark.qrapp.locationRequestPOJO.locationRequestBean;
import com.genuinemark.qrapp.locationRequestPOJO.locationResponseBean;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import github.nisrulz.easydeviceinfo.base.EasyLocationMod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    Toolbar toolbar;

    //DrawerLayout drawerLayout;
    QRCodeReaderView qrCodeReaderView;

    ImageView imageView;

    String lat, lon;

    ProgressBar bar;

    //Timer timer;

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case RequestCameraPermissionID: {

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    try {
                        cameraSource.start(camerapreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }*/

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        /*camerapreview = findViewById(R.id.surface);



        image = findViewById(R.id.image);*/
        imageView = findViewById(R.id.image);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.blackarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        bar = (ProgressBar)findViewById(R.id.progress);

        // toolbar.setTitle("About Us");
        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qrdecoderview);

        qrCodeReaderView.setOnQRCodeReadListener(this);

        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        //qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        //qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();



       /* toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
       /* drawerLayout = findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {


            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
*/
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, Pagers.class);
                startActivity(i);
            }
        });

       /* image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this , Pagers.class);
                startActivity(i);
            }
        });

        text = findViewById(R.id.camera);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();


        camerapreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {


                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);

                    return;
                }
                try {
                    cameraSource.start(camerapreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {


            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                cameraSource.stop();

            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {


            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {


                final SparseArray<Barcode> qrcode = detections.getDetectedItems();
                if (qrcode.size() != 0) {

                    text.post(new Runnable() {
                        @Override
                        public void run() {
                            //create vibrate

                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1);
                            Log.d("asdasdasd", qrcode.valueAt(0).displayValue);
                            text.setText(qrcode.valueAt(0).displayValue);

                            Intent i = new Intent(MainActivity.this, ResultActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("data", qrcode.valueAt(0).displayValue);
                            startActivity(i);
                            finish();

                        }
                    });
                }

            }
        });*/

       /* timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent i = new Intent( MainActivity.this , ResultActivity.class);
                startActivity(i);
                finish();





            }
        }, 1500);*/


        EasyLocationMod easyLocationMod = new EasyLocationMod(MainActivity.this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        double[] l = easyLocationMod.getLatLong();
        lat = String.valueOf(l[0]);
        lon = String.valueOf(l[1]);


    }


    int count = 0;

    @Override
    public void onQRCodeRead(final String text, PointF[] points) {


        if(count == 0)
        {






            bar.setVisibility(View.VISIBLE);

            Bean b = (Bean) getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseurl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiAInterface all = retrofit.create(ApiAInterface.class);


            locationRequestBean body = new locationRequestBean();

            body.setAction("save_user_location");

            Data data = new Data();

            data.setUserId(pref.getString("userId" , ""));
            data.setQrId(text);
            data.setUserLat(lat);
            data.setUserLong(lon);
            body.setData(data);

            count++;

            Call<locationResponseBean> call = all.updateLocation(body);

            call.enqueue(new Callback<locationResponseBean>() {
                @Override
                public void onResponse(Call<locationResponseBean> call, Response<locationResponseBean> response) {

                    if (response.body().getStatus().equals("1"))
                    {
                        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(1);
                        Log.d("asdasdasd", text);
                        //text.setText(qrcode.valueAt(0).displayValue);

                        Intent i = new Intent(MainActivity.this, ResultActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.putExtra("data", text);
                        startActivity(i);
                        overridePendingTransition(0 , 0);
                        finish();


                    }
                    else
                    {
                        Toast.makeText(MainActivity.this , "Invalid QR Code" , Toast.LENGTH_SHORT).show();
                        count = 0;
                    }



                    bar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<locationResponseBean> call, Throwable t) {
                    bar.setVisibility(View.GONE);

                }
            });






        }




    }


   /* @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);


        } else {
            super.onBackPressed();
        }

    }
*/

}
