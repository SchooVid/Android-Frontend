package com.example.schoovid_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapi.Request.SignUpRequest
import com.example.loginapi.Response.SignUpResponse
import com.example.loginapi.Retro
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.signin.*
import kotlinx.android.synthetic.main.signup.*
import kotlinx.android.synthetic.main.signup.password
import retrofit2.Call
import retrofit2.Response

class Signup: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        initAction()

        val actionbar = supportActionBar

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun initAction(){

        buttonSignUp.setOnClickListener(){
            signup()
            if(!username.text.isEmpty() && !password.text.isEmpty() && !lastname.text.isEmpty() && !firstname.text.isEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext, "Please complete the fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun signup(){

        val request = SignUpRequest()
        request.username = username.text.toString().trim()
        request.password = password.text.toString().trim()
        request.role = "ETUDIANT"
        request.lastname = lastname.text.toString().trim()
        request.firstname = firstname.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.signup(request).enqueue(object : retrofit2.Callback<SignUpResponse>{
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                val user = response.body()

            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

        })
    }
}