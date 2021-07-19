package com.example.loginapi.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignUpRequest {

    @SerializedName("id")
    @Expose
    var id:String? = null

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