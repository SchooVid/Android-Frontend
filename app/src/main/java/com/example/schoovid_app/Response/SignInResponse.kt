package com.example.loginapi.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignInResponse {

    @SerializedName("data")
    @Expose
    var data: User? = null

    class User{

        @SerializedName("username")
        @Expose
        var username:String? = null

        @SerializedName("password")
        @Expose
        var password:String? = null

    }

}