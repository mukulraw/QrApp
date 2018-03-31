package com.genuinemark.qrapp.RateProductRequestPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 13-03-2018.
 */

public class Data {

    @SerializedName("qrId")
    @Expose
    private String qrId;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("comment")
    @Expose
    private String comment;

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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
