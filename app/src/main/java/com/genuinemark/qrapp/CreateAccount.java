package com.genuinemark.qrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.genuinemark.qrapp.LoginPOJO.LoginBean;
import com.genuinemark.qrapp.RegisterPOJO.RegiserBean;
import com.genuinemark.qrapp.RegisterRequestPOJO.Data;
import com.genuinemark.qrapp.RegisterRequestPOJO.RegisterRequestBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CreateAccount extends AppCompatActivity {

    Button create;

    EditText dob, name, email;

    RadioGroup group;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        create = findViewById(R.id.create);

        dob = findViewById(R.id.dob);

        name = findViewById(R.id.name);

        email = findViewById(R.id.email);

        group = findViewById(R.id.group);

        bar = findViewById(R.id.progress);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = name.getText().toString();

                String e = email.getText().toString();

                String d = dob.getText().toString();

                if (n.length() > 0) {

                    if (group.getCheckedRadioButtonId() != -1) {

                        if (d.length() > 0) {

                            if (Utils.isValidMail(e)) {

                                bar.setVisibility(View.VISIBLE);

                                Bean b = (Bean) getApplicationContext();

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseurl)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();


                                ApiAInterface all = retrofit.create(ApiAInterface.class);

                                final RegisterRequestBean reg = new RegisterRequestBean();

                                reg.setAction("login");

                                RadioButton bu = findViewById(group.getCheckedRadioButtonId());

                                Data data = new Data();

                                data.setName(n);

                                data.setEmail(e);

                                data.setDob(d);

                                data.setGender(bu.getText().toString());

                                data.setPhone(getIntent().getStringExtra("phone"));

                                reg.setData(data);

                                Call<RegiserBean> call = all.register(reg);

                                call.enqueue(new Callback<RegiserBean>() {
                                    @Override
                                    public void onResponse(Call<RegiserBean> call, Response<RegiserBean> response) {

                                        Toast.makeText(CreateAccount.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(CreateAccount.this, Home.class);

                                        startActivity(i);

                                        bar.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onFailure(Call<RegiserBean> call, Throwable t) {

                                        bar.setVisibility(View.GONE);


                                    }
                                });


                            } else {

                                Toast.makeText(CreateAccount.this, "Please select Gender", Toast.LENGTH_SHORT).show();
                            }


                        } else {

                            Toast.makeText(CreateAccount.this, "Please Enter a EmailID", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(CreateAccount.this, "Please Enter a DOB", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(CreateAccount.this, "Please Enter a  First Name", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
