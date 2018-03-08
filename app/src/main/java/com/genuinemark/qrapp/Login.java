package com.genuinemark.qrapp;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.genuinemark.qrapp.LoginPOJO.LoginBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {

    EditText mobile, otp;

    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login = findViewById(R.id.login);
        mobile = findViewById(R.id.mobile);
        otp = findViewById(R.id.otp);



        String m = mobile.getText().toString();
        String o = otp.getText().toString();

        /*if (Utils.isValidMobile(m)){

            if (o.length()>0){


            }
            else
            {
                Toast.makeText(this, "Please Enter a OTP number", Toast.LENGTH_SHORT).show();
            }


        }else {
            Toast.makeText(this, "Please Enter a Mobile Number", Toast.LENGTH_SHORT).show();
        }

*/


       /* Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseurl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .Build();


        ApiAInterface all = retrofit.create(ApiAInterface.class);

        Call<LoginBean> call = all.login("", "");

        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {


            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

            }
        });
*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, CreateAccount.class);
                startActivity(i);
                finish();

            }
        });
    }
}
