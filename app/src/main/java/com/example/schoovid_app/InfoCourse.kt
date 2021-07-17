package com.example.schoovid_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.callapi.MyDataItem
import com.example.callapi.ServiceGenerator
import com.example.loginapi.Request.SignUpRequest
import com.example.loginapi.Response.SignUpResponse
import com.example.loginapi.Retro
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.info_course.*
import kotlinx.android.synthetic.main.signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoCourse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_course)

        val id = intent.getStringExtra("Id")


        test.text = id

        val actionbar = supportActionBar

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}