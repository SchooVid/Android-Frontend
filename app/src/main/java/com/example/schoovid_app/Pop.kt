package com.example.schoovid_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import com.example.loginapi.Request.CourseProposeDeleteRequest
import com.example.loginapi.Request.SignInRequest
import com.example.loginapi.Retro
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.popup_window.*
import kotlinx.android.synthetic.main.signin.*
import retrofit2.Call
import retrofit2.Response

class Pop : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_window)

        val dm:DisplayMetrics = DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(dm)

        val width = dm.widthPixels
        val height = dm.heightPixels

        getWindow().setLayout(width/1,height/3)

        val courseName = intent.getStringExtra("nameCourse")

        nameCourse.text = "You want to delete " + courseName + " ?"

    }
}
