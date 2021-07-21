package com.example.schoovid_app.Request

import com.google.gson.annotations.SerializedName

data class DataCoursePropose(
    @SerializedName("id")
    val id: String,
    @SerializedName("libelle")
    val libelle: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("userId")
    val userId: String
)