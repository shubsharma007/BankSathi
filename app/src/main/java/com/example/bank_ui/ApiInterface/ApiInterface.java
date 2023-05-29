package com.example.bank_ui.ApiInterface;

import com.example.bank_ui.Model.LoginResponse;
import com.example.bank_ui.Model.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
                                            @Field("password") String password);
}
