package com.genuinemark.qrapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.genuinemark.qrapp.RegisterPOJO.RegiserBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Home extends AppCompatActivity {

    Toolbar toolbar;

    TextView barcode, qrcode;

    DrawerLayout drawerLayout;

    TextView welcome, scan, tutorial, logout;


    SharedPreferences pref;
    SharedPreferences.Editor edit;

    private static final int ACTIVITY_RESULT_QR_DRDROID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        edit = pref.edit();
        welcome = findViewById(R.id.welcome);

        scan = findViewById(R.id.scan);

        tutorial = findViewById(R.id.tutorial);

        logout = findViewById(R.id.logout);

        qrcode = findViewById(R.id.qr);

        barcode = findViewById(R.id.barcode);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.blackarrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        // toolbar.setTitle("");

        //drawerLayout = findViewById(R.id.drawer);

        drawerLayout = findViewById(R.id.drawer);

        String s = pref.getString("name", "");
        welcome.setText("Welcome " + s);

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



        /*qrcode.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent i = new Intent(Home.this , ResultActivity.class);

                try {

                    startActivityForResult(i, ACTIVITY_RESULT_QR_DRDROID);
                }
                catch (ActivityNotFoundException activity) {

                    Home.qrDroidRequired(Home.this);
                }
            }
        });
*/


/*

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .Build();


        ApiAInterface all = retrofit.create(ApiAInterface.class);

        Call<RegiserBean> call = all.register("", "" , "" , "" ,"", "");

*/


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Home.this, MainActivity.class);
                startActivity(i);

            }
        });


        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Home.this, Pagers.class);
                startActivity(i);


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Home.this , Login.class);

                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(i);

                finish();

            }
        });


        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);

            }
        });

        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.notification) {

            Intent i = new Intent(Home.this, Pagers.class);
            startActivity(i);


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);


        } else {
            super.onBackPressed();
        }

    }



    /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( ACTIVITY_RESULT_QR_DRDROID == requestCode
                && data != null && data.getExtras() != null ) {

            String result = data.getExtras().getString("la.droid.qr.result");

            //report.setText(result);
            //report.setVisibility(View.VISIBLE);
        }


    }



    protected static void qrDroidRequired(final Home activity) {
        // TODO Auto-generated method stub

        AlertDialog.Builder AlertBox = new AlertDialog.Builder(activity);

        AlertBox.setMessage("QRDroid Missing");

        AlertBox.setPositiveButton("Direct Download", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub

                activity.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://droid.la/apk/qr/")));
            }
        });

        AlertBox.setNeutralButton("From Market", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                activity.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://market.android.com/details?id=la.droid.qr")));
            }
        });

        AlertBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                dialog.cancel();
            }
        });

        AlertBox.create().show();
    }*/
}


