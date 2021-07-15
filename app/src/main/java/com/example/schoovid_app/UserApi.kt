package com.example.loginapi

import com.example.loginapi.Request.SignInRequest
import com.example.loginapi.Request.SignUpRequest
import com.example.loginapi.Response.SignInResponse
import com.example.loginapi.Response.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("user/")
    fun signup(
        @Body signUpRequest: SignUpRequest
    ): Call<SignUpResponse>

    @POST("auth/signin")
    fun login(
        @Body signInRequest: SignInRequest
    ):Call<SignInResponse>

}