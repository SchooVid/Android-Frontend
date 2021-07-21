package com.example.schoovid_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoovid_app.Request.DataCoursePropose
import com.example.callapi.ServiceGenerator
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.list_course_propose.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCoursePropose: AppCompatActivity(), onCourseProposeItemClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_course_propose)

        val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
        val call = serviceGenerator.getCoursePropose()

        val recyclerView = findViewById<RecyclerView>(R.id.listCoursePropose)

        call.enqueue(object : Callback<MutableList<DataCoursePropose>> {
            override fun onResponse(
                call: Call<MutableList<DataCoursePropose>>,
                response: Response<MutableList<DataCoursePropose>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ListCoursePropose)
                        adapter = PostAdapterCoursePropose(response.body()!!,this@ListCoursePropose)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<DataCoursePropose>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })

        Log.e("TAG", intent.getStringExtra("userId").toString())

        val actionbar = supportActionBar

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemClick(dataCoursePropose: DataCoursePropose, position: Int) {
        val intent = Intent(this, Pop::class.java)
        intent.putExtra("nameCourse", dataCoursePropose.libelle)
        intent.putExtra("courseId", dataCoursePropose.id)
        startActivity(intent)
    }

    fun clickCreateCourse(view: View) {
        btnAddCourse.setOnClickListener {
            val userId = intent.getStringExtra("userId")
            val intent = Intent(this, AddCoursePropose::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }
    }

}