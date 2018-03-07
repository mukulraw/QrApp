package com.genuinemark.qrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* surface = findViewById(R.id.surface);

        SurfaceHolder holder = surface.getHolder();

        holder.addCallback(this);

*/
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent i = new Intent( MainActivity.this , ResultActivity.class);
                startActivity(i);
                finish();





            }
        }, 1500);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {



    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }


}
