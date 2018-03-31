package com.genuinemark.qrapp.SimilarProductRequestPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 12-03-2018.
 */

public class Data {

    @SerializedName("brandId")
    @Expose
    private String brandId;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }


}
