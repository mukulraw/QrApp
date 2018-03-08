package com.genuinemark.qrapp.RateProductPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 08-03-2018.
 */

public class Data {

    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("companyLogo")
    @Expose
    private String companyLogo;
    @SerializedName("imageCount")
    @Expose
    private String imageCount;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("brandName")
    @Expose
    private String brandName;
    @SerializedName("brandLogo")
    @Expose
    private String brandLogo;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("manufacturingDate")
    @Expose
    private String manufacturingDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("quality")
    @Expose
    private String quality;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("isRated")
    @Expose
    private String isRated;
    @SerializedName("myRating")
    @Expose
    private String myRating;
    @SerializedName("recommendation")
    @Expose
    private String recommendation;
    @SerializedName("sharingUrl")
    @Expose
    private String sharingUrl;
    @SerializedName("brandId")
    @Expose
    private String brandId;
    @SerializedName("similarProducts")
    @Expose
    private List<SimilarProduct> similarProducts = null;
    @SerializedName("isVideoAvailable")
    @Expose
    private String isVideoAvailable;
    @SerializedName("videoUri")
    @Expose
    private String videoUri;
    @SerializedName("verificationStatus")
    @Expose
    private String verificationStatus;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getImageCount() {
        return imageCount;
    }

    public void setImageCount(String imageCount) {
        this.imageCount = imageCount;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(String manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIsRated() {
        return isRated;
    }

    public void setIsRated(String isRated) {
        this.isRated = isRated;
    }

    public String getMyRating() {
        return myRating;
    }

    public void setMyRating(String myRating) {
        this.myRating = myRating;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getSharingUrl() {
        return sharingUrl;
    }

    public void setSharingUrl(String sharingUrl) {
        this.sharingUrl = sharingUrl;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public List<SimilarProduct> getSimilarProducts() {
        return similarProducts;
    }

    public void setSimilarProducts(List<SimilarProduct> similarProducts) {
        this.similarProducts = similarProducts;
    }

    public String getIsVideoAvailable() {
        return isVideoAvailable;
    }

    public void setIsVideoAvailable(String isVideoAvailable) {
        this.isVideoAvailable = isVideoAvailable;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }



}
