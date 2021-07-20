package com.example.loginapi

import com.example.loginapi.Request.DataCreateCoursePropose
import com.example.schoovid_app.Request.DataCoursePropose
import com.example.schoovid_app.Request.MyDataItem
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

    @POST("proposed_course/create")
    fun createCoursePropose(
        @Body dataCreateCoursePropose: DataCreateCoursePropose
    ):Call<DataCreateCoursePropose>

    @POST("course_participant/add")
    fun registerCourse(
        @Body registerCourseRequest: RegisterCourseRequest
    ):Call<RegisterCourseRequest>

    @GET("course/all")
    fun getCourse(): Call<MutableList<MyDataItem>>

    @GET("proposed_course/all")
    fun getCoursePropose(): Call<MutableList<DataCoursePropose>>

}