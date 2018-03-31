package com.genuinemark.qrapp.VerifyRequestPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 12-03-2018.
 */

public class VerifyRequestBean {

    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
