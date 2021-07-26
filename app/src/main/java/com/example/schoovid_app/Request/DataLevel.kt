package com.example.schoovid_app.Request

import com.google.gson.annotations.SerializedName

data class DataLevel(
    @SerializedName("id")
    val id: String,
    @SerializedName("libelle")
    val libelle: String
)