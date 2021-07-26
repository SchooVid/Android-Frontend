package com.example.loginapi.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataCreateCoursePropose {

    @SerializedName("libelle")
    @Expose
    var libelle:String? = null

    @SerializedName("description")
    @Expose
    var description:String? = null

    @SerializedName("userId")
    @Expose
    var userId:String? = null

    @SerializedName("niveauId")
    @Expose
    var niveauId:String? = null

    @SerializedName("categorieId")
    @Expose
    var categorieId:String? = null

}
