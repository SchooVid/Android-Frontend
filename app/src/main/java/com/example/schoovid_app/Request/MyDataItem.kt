package com.example.schoovid_app.Request

import com.google.gson.annotations.SerializedName

data class MyDataItem(
    @SerializedName("categorieId")
    val categorieId: String,
    @SerializedName("date_diffusion")
    val dateDiffusion: String,
    @SerializedName("date_fin_diffusion")
    val dateFinDiffusion: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("formateurId")
    val formateurId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("libelle")
    val libelle: String,
    @SerializedName("lien_diffusion")
    val lienDiffusion: Any,
    @SerializedName("niveauId")
    val niveauId: String,
    @SerializedName("firstname")
    val firstnameTeacher: String,
    @SerializedName("lastname")
    val lastnameTeacher: String
)