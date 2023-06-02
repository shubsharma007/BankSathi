package com.example.bank_ui.ApiInterface;

import com.example.bank_ui.Model.CreditCardResponse;
import com.example.bank_ui.Model.LoginResponse;
import com.example.bank_ui.Model.SignUpResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("bslogin/")
    Call<LoginResponse> postLoginResponse(@Field("phoneno") String phoneno,
                                          @Field("password") String password);

    @FormUrlEncoded
    @POST("registeruser/")
    Call<SignUpResponse> postSignUpResponse(@Field("fullname") String fullname,
                                            @Field("username") String username,
                                            @Field("phoneno") String phoneno,
                                            @Field("password") String password,
                                            @Field("is_staff") boolean is_staff);

    @Multipart
    @PATCH("registeruser/{id}/")
    Call<SignUpResponse> updateProfileResponse(@Path("id") int id,
                                               @Part("fullname") RequestBody fullname,
                                               @Part("address") RequestBody address,
                                               @Part("dateofbirth") RequestBody dateofbirth,
                                               @Part("email") RequestBody email,
                                               @Part("pincode") RequestBody pincode,
                                               @Part MultipartBody.Part profileimg);

    @Multipart
    @PATCH("registeruser/{id}/")
    Call<SignUpResponse> updateProfileResponseWithoutImage(@Path("id") int id,
                                                           @Part("fullname") RequestBody fullname,
                                                           @Part("address") RequestBody address,
                                                           @Part("dateofbirth") RequestBody dateofbirth,
                                                           @Part("email") RequestBody email,
                                                           @Part("pincode") RequestBody pincode);

    @GET("registeruser/{id}")
    Call<SignUpResponse> getAllData(@Path("id") int id);

    @GET("creditcard/")
    Call<List<CreditCardResponse>> getCreditCard();

    @GET("creditcard/{id}")
    Call<CreditCardResponse> getSingleCard(@Path("id") int id);

    @GET("demataccount/")
    Call<List<CreditCardResponse>> getDemetAccount();

    @GET("bankaccount/")
    Call<List<CreditCardResponse>> getBankAccount();

    @GET("personalloan/")
    Call<List<CreditCardResponse>> getPersonalLoan();

    @GET("goldloan/")
    Call<List<CreditCardResponse>> getGoldLoan();

    @GET("insurance/")
    Call<List<CreditCardResponse>> getInsurance();
}
