package com.example.loginapi.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignInRequest {

    @SerializedName("id")
    @Expose
    var id:String? = null

    @SerializedName("username")
    @Expose
    var username:String? = null

    @SerializedName("password")
    @Expose
    var password:String? = null

}

/*
{
    companion object{
        val info: MyDataItem? = null
    }
}
 */