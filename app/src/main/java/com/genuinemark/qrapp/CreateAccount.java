package com.genuinemark.qrapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.genuinemark.qrapp.LoginPOJO.LoginBean;
import com.genuinemark.qrapp.RegisterPOJO.RegiserBean;
import com.genuinemark.qrapp.RegisterRequestPOJO.Data;
import com.genuinemark.qrapp.RegisterRequestPOJO.RegisterRequestBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CreateAccount extends AppCompatActivity {

    Button create;

    EditText name, email;

    RadioGroup group;


    TextView dob;

    ProgressBar bar;

    SharedPreferences pref;

    SharedPreferences.Editor edit;

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        edit = pref.edit();

        create = findViewById(R.id.create);

        dob = findViewById(R.id.dob);

        name = findViewById(R.id.name);

        email = findViewById(R.id.email);

        group = findViewById(R.id.group);

        bar = findViewById(R.id.progress);

        checkBox = findViewById(R.id.check);


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(CreateAccount.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog1);
                dialog.setCancelable(true);
                dialog.show();


                final DatePicker picker = dialog.findViewById(R.id.picker);
                Button submit = dialog.findViewById(R.id.submit);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String day = String.valueOf(picker.getDayOfMonth());
                        String month = String.valueOf(picker.getMonth() + 1);
                        String year = String.valueOf(picker.getYear());

                        String date1 = day + "-" + month + "-" + year;

                        dob.setText(date1);

                        dialog.dismiss();


                    }
                });


            }
        });


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

                                if (checkBox.isChecked()) {

                                    bar.setVisibility(View.VISIBLE);

                                    Bean b = (Bean) getApplicationContext();

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(b.baseurl)
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();


                                    ApiAInterface all = retrofit.create(ApiAInterface.class);

                                    final RegisterRequestBean reg = new RegisterRequestBean();

                                    reg.setAction("register");

                                    RadioButton bu = findViewById(group.getCheckedRadioButtonId());

                                    Data data = new Data();

                                    data.setName(n);

                                    data.setEmail(e);

                                    data.setDob(d);

                                    data.setGender(bu.getText().toString());

                                    data.setPhone(getIntent().getStringExtra("phone"));

                                    reg.setData(data);

                                    Log.d("fjklsjg", reg.toString());

                                    Call<RegiserBean> call = all.register(reg);

                                    call.enqueue(new Callback<RegiserBean>() {
                                        @Override
                                        public void onResponse(Call<RegiserBean> call, Response<RegiserBean> response) {

                                            if (Objects.equals(response.body().getStatus(), "1")) {

                                                edit.putString("userId", response.body().getData().getUserId());

                                                edit.putString("name", response.body().getData().getUserId());

                                                edit.putString("phone", response.body().getData().getUserId());

                                                edit.putString("dob", response.body().getData().getUserId());

                                                edit.putString("gender", response.body().getData().getUserId());

                                                edit.putString("email", response.body().getData().getUserId());

                                                edit.apply();

                                                //Toast.makeText(CreateAccount.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                Intent i = new Intent(CreateAccount.this, Home.class);

                                                startActivity(i);

                                                finish();


                                            } else {
                                                Toast.makeText(CreateAccount.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            }


                                            bar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onFailure(Call<RegiserBean> call, Throwable t) {

                                            bar.setVisibility(View.GONE);


                                        }
                                    });


                                } else {
                                    Toast.makeText(CreateAccount.this, "Please accept the conditions", Toast.LENGTH_SHORT).show();
                                }


                            } else {

                                Toast.makeText(CreateAccount.this, "Please Enter a Valid EmailID ", Toast.LENGTH_SHORT).show();
                            }


                        } else {

                            Toast.makeText(CreateAccount.this, "Please Select a DOB ", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(CreateAccount.this, "Please Select a Gender ", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(CreateAccount.this, "Please Enter a Name", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
