package com.example.schoovid_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.loginapi.Request.RegisterCourseRequest
import com.example.loginapi.Retro
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.info_course.*
import kotlinx.android.synthetic.main.signin.*
import kotlinx.android.synthetic.main.signup.*
import retrofit2.Call
import retrofit2.Response

class InfoCourse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_course)

        val courseId = intent.getStringExtra("Id")
        val participantId = intent.getStringExtra("userId")

        courseName.text = intent.getStringExtra("CourseName")
        teacherName.text = "Name teacher : " +intent.getStringExtra("FirstnameTeacher") + " " + intent.getStringExtra("LastnameTeacher")
        description.text = intent.getStringExtra("Description")
        val newDate = intent.getStringExtra("Date")?.replace("T", " ")
        date.text = "Date : " + newDate?.replace(".000Z", "")

        //Log.e("TAG", courseId.toString())
        //Log.e("TAG", userId.toString())

        val actionbar = supportActionBar

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        if (participantId != null) {
            if (courseId != null) {
                initAction(courseId, participantId)
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun initAction(courseId:String,participantId:String){

        btnRegistration.setOnClickListener {
            register(courseId,participantId)
        }

    }

    fun register(courseId: String, participantId: String){

        val request = RegisterCourseRequest()
        request.courseId = courseId
        request.participantId = participantId

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.registerCourse(request).enqueue(object : retrofit2.Callback<RegisterCourseRequest> {
            override fun onResponse(call: Call<RegisterCourseRequest>, response: Response<RegisterCourseRequest>){
                val user = response.body()
                if (response.isSuccessful) {
                    Log.e("TAG", "test")
                    btnRegistration.setVisibility(View.INVISIBLE)
                    /*val intent = Intent(this@InfoCourse, Signin::class.java)
                    startActivity(intent)*/
                }else{
                    Log.e("Erreur", "Not register")
                }
            }

            override fun onFailure(call: Call<RegisterCourseRequest>, t: Throwable) {
                Log.e("Error", "Message :" + t.message)
            }

        })

    }
}