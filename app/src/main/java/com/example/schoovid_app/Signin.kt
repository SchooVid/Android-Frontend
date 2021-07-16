package com.example.schoovid_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.loginapi.Request.SignInRequest
import com.example.loginapi.Response.SignInResponse
import com.example.loginapi.Retro
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.signin.*
import retrofit2.Call
import retrofit2.Response

class Signin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)

        initAction()

    }

    fun clickOnSignUp(view: View) {

        val btnSignup = findViewById<TextView>(R.id.signup)

        btnSignup.setOnClickListener(){
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

    }

    fun initAction(){

        buttonLogin.setOnClickListener {
            login()
            val test = findViewById<TextView>(R.id.errorMessage)
            if(!test.text.isEmpty()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

    }

    fun login(){

        val request = SignInRequest()
        request.username = login.text.toString().trim()
        request.password = password.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.login(request).enqueue(object : retrofit2.Callback<SignInResponse> {
            override fun onResponse(
                call: Call<SignInResponse>,
                response: Response<SignInResponse>
            ) {
                val user = response.body()
                if (response.code() != 200) {
                    errorMessage.setText("Login incorrect")
                }

            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

        })
    }
}