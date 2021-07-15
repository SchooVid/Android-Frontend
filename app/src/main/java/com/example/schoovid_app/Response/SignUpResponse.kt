package com.example.loginapi.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUpResponse {

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

        @SerializedName("role")
        @Expose
        var role:String? = null

        @SerializedName("lastname")
        @Expose
        var lastname:String? = null

        @SerializedName("firstname")
        @Expose
        var firstname:String? = null

    }


}