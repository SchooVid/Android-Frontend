package com.example.loginapi

import com.example.callapi.MyDataItem
import com.example.loginapi.Request.RegisterCourseRequest
import com.example.loginapi.Request.SignInRequest
import com.example.loginapi.Request.SignUpRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @POST("user/")
    fun signup(
        @Body signUpRequest: SignUpRequest
    ): Call<SignUpRequest>

    @POST("auth/signin")
    fun login(
        @Body signInRequest: SignInRequest
    ):Call<SignInRequest>

    @GET("course/all")
    fun getData(): Call<MutableList<MyDataItem>>

    @POST("course_participant/add")
    fun registerCourse(
        @Body registerCourseRequest: RegisterCourseRequest
    ):Call<RegisterCourseRequest>

}