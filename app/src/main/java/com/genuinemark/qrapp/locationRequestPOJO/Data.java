package com.genuinemark.qrapp.locationRequestPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("qrId")
    @Expose
    private String qrId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("userLat")
    @Expose
    private String userLat;
    @SerializedName("userLong")
    @Expose
    private String userLong;

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

    public String getUserLat() {
        return userLat;
    }

    public void setUserLat(String userLat) {
        this.userLat = userLat;
    }

    public String getUserLong() {
        return userLong;
    }

    public void setUserLong(String userLong) {
        this.userLong = userLong;
    }

}
