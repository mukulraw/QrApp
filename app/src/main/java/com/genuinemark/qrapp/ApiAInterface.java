package com.genuinemark.qrapp;

import com.genuinemark.qrapp.ForgotPassword1POJO.ForgotPaassword1Bean;
import com.genuinemark.qrapp.ForgotPassword2POJO.ForgotPassword2Bean;
import com.genuinemark.qrapp.LoginPOJO.LoginBean;
import com.genuinemark.qrapp.LoginRequestPOJO.LoginRequestbean;
import com.genuinemark.qrapp.OtpPOJO.OtpBean;
import com.genuinemark.qrapp.OtpRequestPOJO.OtprequestBean;
import com.genuinemark.qrapp.ProductDetailsPOJO.ProductDetailsBean;
import com.genuinemark.qrapp.RateProductPOJO.RateProductBean;
import com.genuinemark.qrapp.RegisterPOJO.RegiserBean;
import com.genuinemark.qrapp.RegisterRequestPOJO.RegisterRequestBean;
import com.genuinemark.qrapp.SimilarProductPOJO.SimilarProductBean;
import com.genuinemark.qrapp.SocialLogin1POJO.SocialLogin1Bean;
import com.genuinemark.qrapp.SocialLogin2POJO.SocialLogin2Bean;
import com.genuinemark.qrapp.verifyproductPOJO.VerifyProductBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by USER on 08-03-2018.
 */

public interface ApiAInterface {

    @Headers({"Content-Type: application/json"})
    @POST("qrcode/api/api.php")
    Call<OtpBean> otp
            (@Body OtprequestBean otp
            );


    @Headers({"Content-Type: application/json"})
    @POST("qrcode/api/api.php")
    Call<LoginBean> login
            (@Body LoginRequestbean loginbean
            );


    @Headers({"Content-Type: application/json"})
    @POST("qrcode/api/api.php")
    Call<RegiserBean> register
            (@Body RegisterRequestBean register
            );


  /*  @Multipart("")
    Call<SocialLogin1Bean> social1
            (@Part("email") String pjkfdghkhd ,
             @Part("pid") String pdfsgd
            );


    @Multipart("")
    Call<SocialLogin2Bean> social2
            (@Part("userId") String phh ,
             @Part("phone") String pk ,
             @Part("dob") String pll ,
             @Part("gender") String ppp
            );


    @Multipart("")
    Call<ForgotPaassword1Bean> forgot1
            (@Part("email") String jkjkl
            );


    @Multipart("")
    Call<ForgotPassword2Bean> forgot2
            (@Part("code") String pjkfhsjkg ,
             @Part("newPassword") String pmm ,
             @Part("confirmPassword") String pjfks
            );


    @Multipart("")
    Call<ProductDetailsBean> productdetailsbean
            (@Part("productId") String dfjsp ,
             @Part("userId") String hjsdfhp
            );


    @Multipart("")
    Call<RateProductBean> rateproductbean
            (@Part("productId") String pfld ,
             @Part("userId") String pmfsdkf ,
             @Part("rating") String plgl ,
             @Part("coment") String pfsjg
            );



    @Multipart("")
    Call<VerifyProductBean> verifybean
            (@Part("productId") String pdf ,
             @Part("latitude") String plfj ,
             @Part("longitude") String pdmfkjdal ,
             @Part("imei") String pfskdl ,
             @Part("battery") String pdffklda ,
             @Part("userId") String pdfjkd ,
             @Part("verificationCode") String pdfkds
            );


    @Multipart("")
    Call<SimilarProductBean> similar
            (@Part("brandId") String p
            );*/



}
