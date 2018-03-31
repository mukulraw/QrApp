package com.genuinemark.qrapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {


    Timer timer;

    String[] PERMISSIONS = { Manifest.permission.CAMERA , Manifest.permission.ACCESS_COARSE_LOCATION , Manifest.permission.ACCESS_FINE_LOCATION , Manifest.permission.READ_PHONE_STATE};

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        timer = new Timer();





        if(hasPermissions(this , PERMISSIONS))
        {
            startApp();
        }
        else
        {
            ActivityCompat.requestPermissions(this , PERMISSIONS , REQUEST_CODE_ASK_PERMISSIONS);
        }


    }


    public void startApp(){

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent i = new Intent(Splash.this , Login.class);
                startActivity(i);
                finish();


            }
        } , 1500);

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_ASK_PERMISSIONS)
        {
            if (ActivityCompat.checkSelfPermission(getApplicationContext() , Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getApplicationContext() , Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getApplicationContext() , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getApplicationContext() , Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
            {

                startApp();

            }
            else
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.ACCESS_COARSE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.READ_PHONE_STATE)) {

                    Toast.makeText(getApplicationContext() , "Permissions are required for this app" , Toast.LENGTH_SHORT).show();
                    finish();

                }
                //permission is denied (and never ask again is  checked)
                //shouldShowRequestPermissionRationale will return false
                else {
                    Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                            .show();
                    finish();
                    //                            //proceed with logic by disabling the related features or quit the app.
                }
            }

        }


    }

}
