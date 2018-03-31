package com.genuinemark.qrapp.RateProductPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 08-03-2018.
 */

public class SimilarProduct {


    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("qrId")
    @Expose
    private String qrId;
    @SerializedName("product_image")
    @Expose
    private String productImage;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("recommendation")
    @Expose
    private String recommendation;
    @SerializedName("brandLogo")
    @Expose
    private String brandLogo;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }


}
