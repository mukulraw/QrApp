package com.genuinemark.qrapp.ProductDetailsRequestPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 12-03-2018.
 */

public class Data {

    @SerializedName("qrId")
    @Expose
    private String qrId;
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




}
