package com.example.schoovid_app

import com.google.gson.annotations.SerializedName

data class DataCategory (
    @SerializedName("id")
    val id: String,
    @SerializedName("libelle")
    val libelle: String
)
