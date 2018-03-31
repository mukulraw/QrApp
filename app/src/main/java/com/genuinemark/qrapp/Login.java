package com.genuinemark.qrapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.genuinemark.qrapp.LoginPOJO.LoginBean;
import com.genuinemark.qrapp.LoginRequestPOJO.LoginRequestbean;
import com.genuinemark.qrapp.OtpPOJO.OtpBean;
import com.genuinemark.qrapp.OtpRequestPOJO.Data;
import com.genuinemark.qrapp.OtpRequestPOJO.OtprequestBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {

    EditText mobile, otp;

    Button login;

    SharedPreferences pref;

    SharedPreferences.Editor edit;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        edit = pref.edit();

        login = findViewById(R.id.login);

        mobile = findViewById(R.id.mobile);

        otp = findViewById(R.id.otp);

        bar = findViewById(R.id.progress);

        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() == 10) {

                    String m = charSequence.toString();

                    bar.setVisibility(View.VISIBLE);

                    Bean b = (Bean) getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseurl)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    ApiAInterface all = retrofit.create(ApiAInterface.class);

                    OtprequestBean be = new OtprequestBean();

                    be.setAction("otp");

                    Data da = new Data();

                    da.setPhone(m);

                    Log.d("phone", m);

                    be.setData(da);

                    Call<OtpBean> call = all.otp(be);

                    call.enqueue(new Callback<OtpBean>() {
                        @Override
                        public void onResponse(Call<OtpBean> call, Response<OtpBean> response) {

                            try {

                                if (Objects.equals(response.body().getStatus(), "1")) {

                                    if (otp.getVisibility() == View.GONE) {

                                        otp.setVisibility(View.VISIBLE);

                                        Log.d("nisha", response.body().getData().getOtp());

                                        try {

                                            otp.setText(response.body().getData().getOtp());

                                            Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            Log.d("kamal", response.body().getData().getOtp());

                                        } catch (Exception e) {

                                            e.printStackTrace();
                                        }


                                    } else {

                                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                } else {

                                    Intent i = new Intent(Login.this, CreateAccount.class);

                                    i.putExtra("phone", charSequence.toString());

                                    startActivity(i);

                                    finish();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            bar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<OtpBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                        }
                    });


                } else {

                    otp.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String m = mobile.getText().toString();

                String o = otp.getText().toString();

                if (Utils.isValidMobile(m)) {

                    if (o.length() > 0) {

                        bar.setVisibility(View.VISIBLE);

                        Bean b = (Bean) getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseurl)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ApiAInterface all = retrofit.create(ApiAInterface.class);

                        LoginRequestbean log = new LoginRequestbean();

                        log.setAction("login");

                        com.genuinemark.qrapp.LoginRequestPOJO.Data d = new com.genuinemark.qrapp.LoginRequestPOJO.Data();

                        d.setPhone(m);

                        d.setOtp(o);

                        log.setData(d);

                        Call<LoginBean> call = all.login(log);

                        call.enqueue(new Callback<LoginBean>() {
                            @Override
                            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {

                                try {

                                    if (Objects.equals(response.body().getStatus(), "1")) {

                                        edit.putString("userId", response.body().getData().getUserId());

                                        edit.putString("name", response.body().getData().getName());

                                        edit.putString("phone", response.body().getData().getPhone());

                                        edit.putString("dob", response.body().getData().getDob());

                                        edit.putString("gender", response.body().getData().getGender());

                                        edit.putString("email", response.body().getData().getEmail());

                                        edit.apply();


                                        Intent i = new Intent(Login.this, Home.class);

                                        startActivity(i);

                                        finish();


                                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                                    } else {

                                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }


                                } catch (Exception e) {

                                    e.printStackTrace();
                                }

                                bar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<LoginBean> call, Throwable t) {

                                bar.setVisibility(View.GONE);

                            }
                        });


                    } else {
                        Toast.makeText(Login.this, "Please Enter a OTP number", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(Login.this, "Please Enter a Mobile Number", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
