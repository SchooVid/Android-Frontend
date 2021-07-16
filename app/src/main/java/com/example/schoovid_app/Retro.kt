package com.example.loginapi

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {

    fun getRetroClientInstance(): Retrofit {

        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("http://51.178.139.94:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}