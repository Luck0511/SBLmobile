package com.itlvck.sblmobile.service;

import com.itlvck.sblmobile.dto.LoginRequest;
import com.itlvck.sblmobile.dto.LoginResponse;
import com.itlvck.sblmobile.dto.RegisterRequest;
import com.itlvck.sblmobile.dto.RegisterResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiServicies {
    //Register
    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    //Login
    @POST("loginr")
    Call<LoginResponse> login(@Body LoginRequest request);
}
