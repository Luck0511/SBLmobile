package com.itlvck.sblmobile.service;

import com.itlvck.sblmobile.dto.BookItem;
import com.itlvck.sblmobile.dto.LoginRequest;
import com.itlvck.sblmobile.dto.LoginResponse;
import com.itlvck.sblmobile.dto.RegisterRequest;
import com.itlvck.sblmobile.dto.RegisterResponse;
import com.itlvck.sblmobile.dto.TrendingResponse;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;


public interface ApiServicies {
    //Register
    @POST("api/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    //Login
    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    //Trending & Research
    @GET("api/getTrending")
    Call<TrendingResponse> getTrending();

    //Research
    @GET("api/searchBook")
    Call<BookItem.ResearchResponse> searchBooks(@QueryMap Map<String, String> queries);

}
