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
import retrofit2.Callback
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
        }

    }

    fun signup(){

        val request = SignUpRequest()
        request.username = username.text.toString().trim()
        request.password = password.text.toString().trim()
        val confirmPassword = confirmPassword.text.toString().trim()
        request.role = "ETUDIANT"
        request.lastname = lastname.text.toString().trim()
        request.firstname = firstname.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.signup(request).enqueue(object : Callback<SignUpRequest> {
            override fun onFailure(call: Call<SignUpRequest>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

            override fun onResponse(call: Call<SignUpRequest>, response: Response<SignUpRequest>) {
                val user = response.body()
                if(!username.text.isEmpty() && !password.text.isEmpty() && !lastname.text.isEmpty() && !firstname.text.isEmpty()) {
                    if(request.password == confirmPassword){
                        Toast.makeText(applicationContext, "Your account has been created", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Signup, Signin::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext, "Password not match", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(applicationContext, "Please complete the fields", Toast.LENGTH_SHORT).show()
                }

            }

        })
    }
}