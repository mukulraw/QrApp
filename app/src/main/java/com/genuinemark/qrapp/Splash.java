package com.genuinemark.qrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {


    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Intent i = new Intent(Splash.this , Login.class);
                startActivity(i);


            }
        } , 1500);


    }
}
