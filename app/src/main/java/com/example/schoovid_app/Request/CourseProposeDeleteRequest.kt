package com.example.loginapi.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CourseProposeDeleteRequest {

    @SerializedName("id")
    @Expose
    var id:String? = null

    @SerializedName("libelle")
    @Expose
    var libelle:String? = null

    @SerializedName("description")
    @Expose
    var description:String? = null

}