package com.itlvck.sblmobile.service;

import com.itlvck.sblmobile.dto.LoginRequest;
import com.itlvck.sblmobile.dto.LoginResponse;
import com.itlvck.sblmobile.dto.RegisterRequest;
import com.itlvck.sblmobile.dto.RegisterResponse;
import com.itlvck.sblmobile.dto.TrendingResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiServicies {
    //Register
    @POST("api/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    //Login
    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    //Trending
    @GET("api/getTrending")
    Call<TrendingResponse> getTrending();

}
