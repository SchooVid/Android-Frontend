package com.example.loginapi

import com.example.loginapi.Request.*
import com.example.schoovid_app.DataCategory
import com.example.schoovid_app.Request.DataCoursePropose
import com.example.schoovid_app.Request.DataLevel
import com.example.schoovid_app.Request.MyDataItem
import retrofit2.Call
import retrofit2.http.*

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

    @POST("course_participant/one")
    fun compareRegister(
        @Body registerCourseRequest: RegisterCourseRequest
    ):Call<Boolean>

    @GET("course/all")
    fun getCourse(): Call<MutableList<MyDataItem>>

    @GET("proposed_course/all")
    fun getCoursePropose(): Call<MutableList<DataCoursePropose>>

    // Route get all category : /course_category
    @GET("/course_category/")
    fun getCategory(): Call<MutableList<DataCategory>>

    // Route get all level : /course_level
    @GET("/course_level/")
    fun getLevel(): Call<MutableList<DataLevel>>

    @DELETE("proposed_course/")
    fun deleteCoursePropose(@Path("id") id: String): Call<Unit>
}