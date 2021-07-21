package com.example.schoovid_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoovid_app.Request.MyDataItem
import com.example.callapi.ServiceGenerator
import com.example.loginapi.UserApi
import kotlinx.android.synthetic.main.list_course_propose.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCourse : AppCompatActivity(), onCourseItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_course)

        val serviceGenerator = ServiceGenerator.buildService(UserApi::class.java)
        val call = serviceGenerator.getCourse()

        val recyclerView = findViewById<RecyclerView>(R.id.listCourse)

        call.enqueue(object : Callback<MutableList<MyDataItem>> {
            override fun onResponse(
                call: Call<MutableList<MyDataItem>>,
                response: Response<MutableList<MyDataItem>>
            ) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ListCourse)
                        adapter = PostAdapter(response.body()!!,this@ListCourse)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<MyDataItem>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

        })

        val actionbar = supportActionBar

        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemClick(myDataItem: MyDataItem, position: Int) {
        val userId = intent.getStringExtra("userId")
        val intent = Intent(this, InfoCourse::class.java)
        intent.putExtra("Id", myDataItem.id)
        intent.putExtra("userId", userId)
        intent.putExtra("CourseName", myDataItem.libelle)
        intent.putExtra("FirstnameTeacher", myDataItem.firstnameTeacher)
        intent.putExtra("LastnameTeacher", myDataItem.lastnameTeacher)
        intent.putExtra("Description", myDataItem.description)
        intent.putExtra("Date", myDataItem.dateDiffusion)
        startActivity(intent)
    }
}