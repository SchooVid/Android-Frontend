package com.example.schoovid_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import com.example.callapi.ServiceGenerator
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.popup_window.*
import retrofit2.Call
import retrofit2.Callback
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

        val courseId = intent.getStringExtra("courseId")
        val courseName = intent.getStringExtra("nameCourse")

        nameCourse.text = "You want to delete " + courseName + " ?"

        action(courseId.toString())
    }

    fun action(courseId: String){
        btnDelete.setOnClickListener {
            val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
            val call = serviceGenerator.deleteCoursePropose(courseId)

            call.enqueue(object: Callback<Unit>{

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.e("Error", t.localizedMessage)
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {

                    if(response.isSuccessful){
                        Toast.makeText(applicationContext, "Course is delete", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Pop, ListCoursePropose::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext, "Course not delete", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
        btnCancel.setOnClickListener {
            val intent = Intent(this@Pop, ListCoursePropose::class.java)
            startActivity(intent)
        }
    }
}
